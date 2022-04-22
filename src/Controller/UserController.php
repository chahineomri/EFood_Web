<?php

namespace App\Controller;

use App\Controller\uploader;
use App\Entity\User;
use App\Form\UserType;
use App\Form\UserTypeUpdateType;
use App\Repository\UserRepository;
use phpDocumentor\Reflection\Type;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\FormType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Form;
use Symfony\Component\Form\FormInterface;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;
use Symfony\Component\HttpFoundation\Session\Session;
use Symfony\Component\Mime\Email;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Bridge\Twig\Mime\TemplatedEmail;

use Flasher\SweetAlert\Prime\SweetAlertFactory;


class UserController extends AbstractController
{

    /**
     * @Route("/user", name="user")
     */
    public function index(): Response
    {
        return $this->render('user/profile.html.twig', [
            'controller_name' => 'UserController',
        ]);
    }
    /**
     * @Route("/createAccount", name="app_createAccount")
     */
    public function createAccount(MailerInterface $mailer,Request $request,UserPasswordEncoderInterface $passwordEncoder,uploader $uploader)
    {
        $form = $this->createForm(UserType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted()&& $form->isValid()) {
            $user = $form->getData();
            $user->setPassword($passwordEncoder->encodePassword($user, $form['plainPassword']->getData()));
            if (true === $form['agreeTerms']->getData()) {
            $user->agreeTerms();}
            $uploadedFile = $form['profilepicuser']->getData();
            if ($uploadedFile) {
                $newFilename = $uploader->uploadProfilePic($uploadedFile);
                $user->setImageFilename($newFilename);
            }
            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();
            $email = (new TemplatedEmail())
                ->from('efoodappproject@gmail.com')
                ->to($user->getEmailuser())
                ->subject('Profile created')
                ->htmlTemplate('email/welcome.html.twig');
            $mailer->send($email);
            $this->addFlash('success', 'Profile Created!');
            return $this->render('user/createAccount.html.twig', array(
                'formA' => $form->createView()));
         }
        return $this->render('user/createAccount.html.twig', array(
            'formA' => $form->createView()));

    }

    /**
     * @Route("/profile/{id}/edit", name="app_user_edit", methods={"GET", "POST"})
     */
    public function edit(SweetAlertFactory $flasher,UserPasswordEncoderInterface $passwordEncoder,MailerInterface $mailer,Request $request, User $user,uploader $uploader)
    {
        $form = $this->createForm(UserTypeUpdateType::class,$user);
        $form->handleRequest($request);
        if ($form->isSubmitted()&& $form->isValid()) {
            $user = $form->getData();
            $uploadedFile = $form['profilepicuser']->getData();
            if ($uploadedFile) {
                $newFilename = $uploader->uploadProfilePic($uploadedFile);
                $user->setImageFilename($newFilename);
            }
            $oldPassword = $form['oldPassword']->getData();
            if($passwordEncoder->isPasswordValid($user,$oldPassword)){
            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();
            $email = (new TemplatedEmail())
                ->from('efoodappproject@gmail.com')
                ->to($user->getEmailuser())
                ->subject('Profile updated')
                ->htmlTemplate('email/update.html.twig');
            $mailer->send($email);
            $flasher->addSuccess('Data has been saved successfully!');
            return $this->redirectToRoute('app_profile', [
                'id' => $user->getIduser(),
            ]);

        }
            $this->addFlash('notice', 'Invalid password entered');
            return $this->render('user/edit.html.twig', array(
                'form' => $form->createView()));
        }

        return $this->render('user/edit.html.twig', array(
            'form' => $form->createView()));

    }
    /**
     * @Route("/profile/{id}/delete", name="app_user_delete")
     */
    public function deleteUser($id)
    {
        $currentUserId = $this->getUser()->getId();
        $session = $this->get('session');

        if ($currentUserId == $id)
        {
            $session = new Session();
            $session->invalidate();
        }
        $em = $this->getDoctrine()->getManager();
        $usrRepo = $em->getRepository(User::class);
        $user = $usrRepo->find($id);
        $em->remove($user);
        $em->flush();
        $session->invalidate();
        return $this->redirectToRoute('app_login');
    }

}
