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
use App\Entity\CommandeInformation;
use App\Repository\CommandeInformationRepository;
use App\Repository\CommandeRepository;
use Doctrine\Persistence\ManagerRegistry;

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
    public function unbanUserAction(SweetAlertFactory $flasher,MailerInterface $mailer,$userID){

        $user = $this->getDoctrine()->getRepository(User::class)->find($userID);
        $user->setUserstatus(1);
        $em = $this->getDoctrine()->getManager();
        $em->persist( $user );
        $em->flush();
        $flasher->addSuccess('User unbanned successfully!');
        $email = (new TemplatedEmail())
            ->from('efoodappproject@gmail.com')
            ->to($user->getEmailuser())
            ->subject('Profile updated')
            ->htmlTemplate('email/unbanned.html.twig');
        $mailer->send($email);
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


    /**
     * @Route("/commandes", name="admin_commandes")
     */
    public function commandes(UserRepository $userRepository): Response
    {
        return $this->render('admin/commandes.html.twig', [
            'users' => $userRepository->findAll(),
        ]);
    }



    /**
     * @Route("/getAllCommands", name="get_commands")
     */
    public function getAllCommands(CommandeRepository $commandeRepository)
    {

        $criteria = new \Doctrine\Common\Collections\Criteria();
        $criteria->where(\Doctrine\Common\Collections\Criteria::expr()->gt('prize', 200));

        $commandes = $commandeRepository->findBy(array('etat' => 'En_cour'));
        $data = [];
        foreach ($commandes as $commande) {
            $userFullName = $commande->getUser()->getUsername();
            $commandeId = $commande->getId();
            $etatCommande = $commande->getEtat();
            $totale = $commande->getTotale();
            foreach ($commande->getCommandeInformations() as $commandeInfo) {
                $productname = $commandeInfo->getProduct()->getName();
                $productprice = $commandeInfo->getProduct()->getPrice();
                $productimage = $commandeInfo->getProduct()->getImage();
                $productQuantity = $commandeInfo->getQuantity();
            }
            $post_data = (object)['userFullName' => $userFullName,
                'etatCommande' => $etatCommande,
                'commandeId' => $commandeId,
                'totale' => $totale,
                'productname' => $productname,
                'productprice' => $productprice,
                'productQuantity' => $productQuantity,
                'productimage' => $productimage
            ];
            array_push($data, $post_data);
        }
        $response = new Response(json_encode(array('data' => $data)));
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }

    /**
     * @Route("/validCommande/{id}", name="valid_commands")
     */
    public function validerCommande(int                $id,
                                    CommandeRepository $commandeRepository,
                                    ManagerRegistry    $managerRegistry)
    {



        $manager = $managerRegistry->getManager();
        $commande = $commandeRepository->find($id);
        $commande->setEtat("Validé");
        $manager->persist($commande);
        $manager->flush();
        return $this->render('admin/index.html.twig', []);
    }

    /**
     * @Route("/refuseCommand/{id}", name="refuse_commands")
     */
    public function refuserCommande(int                $id,
                                    CommandeRepository $commandeRepository,
                                    ManagerRegistry    $managerRegistry)
    {

        $manager = $managerRegistry->getManager();
        $commande = $commandeRepository->find($id);
        $commande->setEtat("Refusé");
        $manager->persist($commande);
        $manager->flush();
        return $this->redirectToRoute('app_admin');

    }
}
