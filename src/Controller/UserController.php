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

class UserController extends AbstractController
{

    /**
     * @Route("/user", name="user_app")
     */
    public function index(UserRepository $userRepository): Response
    {
        return $this->render('user/index.html.twig', [
            'users' => $userRepository->findAll(),
        ]);
    }
    /**
     * @Route("/createAccount", name="app_createAccount")
     */
    public function createAccount(Request $request,UserPasswordEncoderInterface $passwordEncoder,uploader $uploader)
    {
        $user=new User();
        $form = $this->createForm(UserType::class,$user);
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
            $this->addFlash('success', 'Profile Created!');
            return $this->render('user/profile.html.twig', array(
                'formA' => $form->createView()));
         }
        return $this->render('user/createAccount.html.twig', array(
            'formA' => $form->createView()));

    }

    /**
     * @Route("/profile/{id}/edit", name="app_user_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, User $user,UserPasswordEncoderInterface $passwordEncoder,uploader $uploader)
    {
        $form = $this->createForm(UserTypeUpdateType::class,$user);
        $form->handleRequest($request);
        if ($form->isSubmitted()&& $form->isValid()) {
            $user = $form->getData();
            $user->setPassword($passwordEncoder->encodePassword($user, $form['plainPassword']->getData()));
            $uploadedFile = $form['profilepicuser']->getData();
            if ($uploadedFile) {
                $newFilename = $uploader->uploadProfilePic($uploadedFile);
                $user->setImageFilename($newFilename);
            }
            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();
            $this->addFlash('success', 'Profile updated!');
            return $this->redirectToRoute('app_user_edit', [
                'id' => $user->getIduser(),
            ]);

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
    }

}
