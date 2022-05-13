<?php

namespace App\Controller;

use App\Entity\Restaurant;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Encoder\JsonEncode;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Normalizer\NormalizableInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Serializer;
use \App\Entity\Menu;
use \App\Entity\Note;
use \App\Entity\Client;
use App\Repository\RestaurantRepository;
use App\Repository\MenuRepository;
use App\Repository\ClientRepository;
use App\Repository\NoteRepository;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use App\Form\RestaurantType;
use App\Form\ContactType;
use App\Form\MenuType;
use App\Form\NoteType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;

class MenuController extends AbstractController
{
    /**
     * @Route ("/menu/{id}", name="menu_show")
     */
    public function show_menu(MenuRepository $repo, $id){
        //$repo = $this -> getDoctrine()->getRepository(Restaurant::class);

        $menus = $repo->findBy(array('idRestaurant' => $id));
        return $this->render('menu/menu.html.twig',[
            'controller_name' => 'ProjetController','menus' => $menus
        ]);
    }

    /**
     * @Route ("/menuAll", name="menuAll")
     */
    public function show_menuAll(MenuRepository $repo){
        //$repo = $this -> getDoctrine()->getRepository(Restaurant::class);

        $menus = $repo->findAll();
        return $this->render('menu/menuAll.html.twig',[
            'controller_name' => 'ProjetController','menus' => $menus
        ]);
    }

    /**
     * @Route("/gerant/delete/{id}", name="gerant_delete")
     */
    public function delete_gerant($id){
        $repository = $this->getDoctrine()->getRepository(Menu::class);
        $menu = $repository->find($id);

        $em = $this->getDoctrine()->getManager();
        $em ->remove($menu);
        $em ->flush();

        return $this->redirectToRoute('app_projet_back');
    }

    /**
     * @Route("/gerant/new", name="add_gerant")
     */
    public function add_gerant(Request $request){
        $menu = new menu();

        $form = $this->createForm(MenuType::class, $menu);
        $form->add('Ajout',SubmitType::class);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()){
            $manager = $this->getDoctrine()->getManager();
            $manager->persist($menu);
            $manager->flush();

            return $this->redirectToRoute('menuAll', [
                'id' => $menu->getId()
            ]);
        }
        return $this->render('menu/create_menu.html.twig',[
            'controller_name' => 'ProjetController','formResto' => $form->createView()
        ]);
    }

    /**
     * @Route("/gerant/delete/{id}", name="gerant_delete")
     */
    public function delete_menu($id){
        $repository = $this->getDoctrine()->getRepository(Menu::class);
        $menu = $repository->find($id);

        $em = $this->getDoctrine()->getManager();
        $em ->remove($menu);
        $em ->flush();

        return $this->redirectToRoute('menuAll');
    }

    /**
     * @Route("/gerant/update/{id}", name="gerant_update")
     */
    public function update_menu(Request $request, $id){
        $repository = $this->getDoctrine()->getRepository(Menu::class);
        $menu = $repository->find($id);

        $form = $this->createForm(MenuType::class, $menu);
        $form ->add('Upadte', SubmitType::class);
        $form ->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()){
            $em = $this->getDoctrine()->getManager();
            $em ->persist($menu);
            $em ->flush();

            return $this->redirectToRoute('menuAll', [
                'controller_name' => 'ProjetController','id' => $menu->getId()
            ]);
        }

        return $this->render('menu/create_menu.html.twig',[
            'controller_name' => 'ProjetController','formResto' => $form->createView()
        ]);
    }

    /**
     * @Route("menu/promotion/{id}" , name="promotion")
     */
    public function promotion(MenuRepository $repo, $id){
        $repository = $this->getDoctrine()->getRepository(Menu::class);
        $menus = $repository->findBy(array('idRestaurant' => $id), array('datePeremption' < "-10 days"));
        return $this->render('menu/menu.html.twig',[
            'controller_name' => 'ProjetController','menus' => $menus
        ]);
    }

    /**
     * @Route("/menu/like/{id}", name="addLikeMenu")
     */
    public function addLike($id){

        $repository = $this->getDoctrine()->getRepository(Menu::class);
        $menu = $repository->find($id);

        $menu->setLikeMenu($menu->getLikeMenu() + 1);

        //if(restaurant->setIdClient != $idClient){
        $manager = $this->getDoctrine()->getManager();
        $manager ->persist($menu);
        $manager->flush();
        //}

        return $this->redirectToRoute('menu_show', [
            'id' => $menu->getIdRestaurant()
        ]);
    }

    /**
     * @Route("/menu/dislike/{id}", name="adddisLikeMenu")
     */
    public function adddisLike($id){

        $repository = $this->getDoctrine()->getRepository(Menu::class);
        $menu = $repository->find($id);

        $menu->setDislikeMenu($menu->getDislikeMenu() + 1);

        $manager = $this->getDoctrine()->getManager();
        $manager ->persist($menu);
        $manager->flush();

        return $this->redirectToRoute('menu_show', [
            'id' => $menu->getIdRestaurant()
        ]);
    }

    /*public function mailing(Request $request, \Swift_Mailer $mailer){

        $form = $this->createForm(ContactType::class);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()){
            $contact = $form->getData();

            $message = (new \Swift_Message('Nouveau Contact'))
                ->setFrom('leoreborn345@gmail.com')
                ->setTo($contact['email'])
                ->setBody(
                    $this->renderView(
                        'emails/email.html.twig', compact('contact')
                    ),
                    'text/html'
                );

            $mailer->send($message);
            $this->addFlash('message', 'Le meessage a bien été envoyé');
            return $this->redirectToRoute('mailing');
        }
        return $this->render('emails/contact.html.twig',[
            'controller_name' => 'ProjetController','formEmail' => $form->createView()
        ]);
    }*/

    /**
     * @Route ("/menu/note/{id}", name="menu_note")
     */
    public function note_menu(Request $request, MenuRepository $repo, ClientRepository $repoClient, NoteRepository $repoNote, $id){

        $menus = $repo->find($id);
        $note = $repoNote->findBy(array('Menu' => $id));
        
        return $this->render('menu/note.html.twig',[
            'controller_name' => 'ProjetController','menus' => $menus/*, 'formNote' => $form->createview()*/
        ]);
    }

    /**
     * @Route ("/menu/note/envoie/{id}/{idMenu}", name="menu_note_envoie")
     */
    public function ajouter_note(Request $request, ClientRepository $repoClient, MenuRepository $repoMenu, $id, $idMenu){
        $note = new note();
        $idC = 1;
        $menu = $repoMenu->find($idMenu);
        $client = $repoClient->find($idC);
        $no = $_POST["note"];
        $manager = $this->getDoctrine()->getManager();
        $note->setIdClient($client);
        $note->setMenu($menu);
        $note->setNote($no);
        $manager->persist($note);
        $manager->flush();

        return $this->redirectToRoute('menu_show', [
            'id' => $id
        ]);
    }

    //*********************************supprimer avec json****************************//

    /**
     * @Route("/suppRestaurant", name="supp_reclamation")
     */

    public function deleteRestaurantAction(Request $request)
    {
       /* $id = $request->get("id");

        $em = $this->getDoctrine()->getManager();
        $reclamation = $em->getRepository(Reclamation::class)->find($id);
        if ($reclamation != null) {
            $em->remove($reclamation);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("Reclamation a ete supprimee avec success.");
            return new JsonResponse($formatted);

        }
        return new JsonResponse("id reclamation invalide.");
*/
    }



    //*******************modifier avec json*****************//
    /**
     * @Route("/modRestaurant", name="mod_reclamation")
     */
    public function modifierRestaurantAction(Request $request) {
      /*  $em = $this->getDoctrine()->getManager();
        $reclamation = $em->getRepository(Reclamation::class)
            ->find(
                $request->get("id")
            );
        // $reclamation->setIdCommande($request->get("idCommande"));
        $reclamation->setCategorie($request->get("categorie"));
        $reclamation->setSujet($request->get("sujet"));
        $reclamation->setDescription($request->get("description"));
        $reclamation->setDate(new \DateTime());

        $em->persist($reclamation);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($reclamation);
        return new JsonResponse("Reclamation a ete modifiee avec success.");*/

    }


    //*************************ajouter avec json*****************************//


    /**
     * @Route("/ajoutRestaurant", name="ajout_reclamation")
     */
    public function ajouterRestaurantAction(Request $request) {

/*
        $em = $this->getDoctrine()->getManager();
        $restaurant= new Restaurant();



        $restaurant->setIdCommande("0");
        $restaurant->setCategorie($request->get("categorie"));
        $restaurant->setSujet($request->get("sujet"));
        $restaurant->setDescription($request->get("description"));
        $restaurant->setDate(new \DateTime());
        $em->persist($restaurant);
        $em->flush();
        /*$jsoncontent= $normalizer->normalize($reclamation,'json',['group'=>'post:read']);
        return new Response(json_encode($jsoncontent));*/
       /* $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($restaurant);
        return new JsonResponse(" le Restaurant a ete ajouté avec success.");*/

    }

//***************afficher les reclamations avec json*************//

    /*
     * @Route("/affReclamation", name="afficher_reclamation")


public function allrecl(){

        $reclamation=$this->getDoctrine()->getManager()->getRepository(Reclamation::class)->findAll();
        $serializer= new Serializer([new ObjectNormalizer()]);
        $formatted= $serializer->normalize($reclamation);

        return new JsonResponse($formatted);

}*/
    /**
     * @Route("/affRestaurant", name="afficher_reclamation")
     */
    public function allrest(NormalizerInterface $normalizer,MenuRepository $repo): Response
    {
        $repo=$this->getDoctrine()->getRepository(Menu::class) ;
        $menu=$repo->findAll();
        $jsonContent=$normalizer->normalize($menu,'json',['groups'=>'post:read']);


        return new Response(json_encode($jsonContent));

        dump($jsonContent);
        die;

    }

//**************detail reclamation**********************//

    /**
     * @Route("/detRestaurant", name="detail_reclamation")
     */

    public function detailRestaurant(Request $request)
    {
       /* $id=$request->get("id");
        $em=$this->getDoctrine()->getManager();
        $reclamation=$em->getRepository(Reclamation::class)->find($id);
        $encoder= new JsonEncoder();
        $normalizer=new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object){
            return $object->getDescription;
        });
        $serializer= new Serializer([new ObjectNormalizer()]);
        $formatted= $serializer->normalize($reclamation);

        return new JsonResponse($formatted);*/


    }




}
