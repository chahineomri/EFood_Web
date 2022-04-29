<?php

namespace App\Controller;

use App\Entity\User;
use App\Repository\UserRepository;
use Flasher\SweetAlert\Prime\SweetAlertFactory;
use Symfony\Bridge\Twig\Mime\TemplatedEmail;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;

/**
 * @IsGranted("ROLE_ADMIN")
 */
class AdminController extends AbstractController
{
    /**
     * @Route("/admin", name="app_admin",methods={"GET"})
     */
    public function index(UserRepository $userRepository): Response
    {
        return $this->render('admin/index.html.twig', [
            'users' => $userRepository->findAll(),
        ]);
    }
    /**
     * @Route("/admin/ban/{userID}",
     * defaults={"userID" = 0},
     * name="ban")
     */
    public function banUserAction(SweetAlertFactory $flasher,MailerInterface $mailer,$userID){

        $user = $this->getDoctrine()->getRepository(User::class)->find($userID);
        $user->setUserstatus(0);
        $em = $this->getDoctrine()->getManager();
        $em->persist( $user );
        $em->flush();
        $flasher->addSuccess('User banned successfully!');
        $email = (new TemplatedEmail())
            ->from('efoodappproject@gmail.com')
            ->to($user->getEmailuser())
            ->subject('Banned')
            ->htmlTemplate('email/banned.html.twig');
        $mailer->send($email);
        return $this->redirectToRoute('app_admin');
    }
    /**
     * @Route("/admin/unban/{userID}",
     * defaults={"userID" = 0},
     * name="unban")
     */
    public function unbanUserAction(SweetAlertFactory $flasher,$userID){

        $user = $this->getDoctrine()->getRepository(User::class)->find($userID);
        $user->setUserstatus(1);
        $em = $this->getDoctrine()->getManager();
        $em->persist( $user );
        $em->flush();
        $flasher->addSuccess('User unbanned successfully!');
        return $this->redirectToRoute('app_admin');
    }

    /**
     * @Route("/admin/show/{userID}", name="app_admin_show", methods={"GET"})
     */
    public function show($userID,UserRepository $userRepository)
    {
        return $this->render('admin/ListUser.html.twig',[
            'user' => $userRepository->find($userID)
        ]);
    }



    /**
     *
     * @Route("/{iduser}", name="app_admin_delete", methods={"POST"})

    public function delete(Request $request, User $user, UserRepository $userRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$user->getIduser(), $request->request->get('_token'))) {
            $userRepository->remove($user);
        }
        return $this->redirectToRoute('app_admin', [], Response::HTTP_SEE_OTHER);
    }
     */
}
