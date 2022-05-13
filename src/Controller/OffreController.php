<?php

namespace App\Controller;

use App\Entity\Offre;
use App\Controller\uploader;

use App\Form\Offre1Type;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\HttpFoundation\JsonResponse;

use Symfony\Component\Serializer\Normalizer\NormalizerInterface;


/**
 * @Route("/offre")
 */
class OffreController extends AbstractController
{
    /**
     * @Route("/", name="app_offre_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
        $offres = $entityManager
            ->getRepository(Offre::class)
            ->findAll();

        return $this->render('offre/index.html.twig', [
            'offres' => $offres,
        ]);
    }
    /**
     * @Route("/try", name="app_offre_indexfront", methods={"GET"})
     */
    public function indexfront(EntityManagerInterface $entityManager): Response
    {
        $offres = $entityManager
            ->getRepository(Offre::class)
            ->findAll();

        return $this->render('offre/indexfront.html.twig', [
            'offres' => $offres,
        ]);
    }
    /**
     * @Route("/get", name="app_offre_try")
     */
    public function showall(NormalizerInterface $Normalizer )
    {
        $repository = $this->getDoctrine()->getRepository(Offre::class);
        $offres = $repository->findAll();
        $jsonContent = $Normalizer->normalize($offres,'json', ['groups'=>'post:read']);

        return new JsonResponse($jsonContent);

    }


    /**
     * @Route("/new", name="app_offre_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager,uploader $uploader): Response
    {
        $offre = new Offre();
        $form = $this->createForm(Offre1Type::class, $offre);
        $form->handleRequest($request);


        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($offre);
            $entityManager->flush();
            $uploadedFile = $form['imgOffre']->getData();
            if ($uploadedFile) {

                $newFilename = $uploader->uploadOfferPic($uploadedFile);
                $offre->setImageFilename($newFilename);
            }

            return $this->redirectToRoute('app_offre_index', [], Response::HTTP_SEE_OTHER);
        }


        return $this->render('offre/new.html.twig', [
            'offre' => $offre,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/addoffreJSON", name="addoffreJSON",)
     *
     */

    public function addoffreJSON(Request $request, NormalizerInterface $Normalizer)
    {
        $em = $this->getDoctrine()->getManager () ;
      $offre = new Offre();

        $offre->setTextOffre ($request->get( 'textOffre'));
        $offre->setDateOffre ($request->get('dateOffre')) ;
     $offre->setTypeOffre ($request->get('typeOffre')) ;
     $offre->setImgOffre ($request->get('imgOffre')) ;

     $em->persist($offre);
      $em->flush();
    $jsonContent = $Normalizer->normalize($offre, 'json', ['groups'=> 'post:read' ]);

        return $this->render('offre/indexjson.html.twig', [
            'data'=>$jsonContent
        ]);

    }


    /**
     * @Route("/{idOffre}", name="app_offre_show", methods={"GET"})
     */
    public function show(Offre $offre): Response
    {
        return $this->render('offre/show.html.twig', [
            'offre' => $offre,
        ]);
    }

    /**
     * @Route("/{idOffre}/edit", name="app_offre_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Offre $offre, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(Offre1Type::class, $offre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_offre_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('offre/edit.html.twig', [
            'offre' => $offre,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idOffre}", name="app_offre_delete", methods={"POST"})
     */
    public function delete(Request $request, Offre $offre, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$offre->getIdOffre(), $request->request->get('_token'))) {
            $entityManager->remove($offre);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_offre_index', [], Response::HTTP_SEE_OTHER);
    }

    /**
     * @Route("/displayoffers", name="app_offre_allRecAction",  methods={"GET"})
     */
    public function allRecAction()
    {

        $offre = $this->getDoctrine()->getManager()->getRepository(Offre::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($offre);

        return new JsonResponse($formatted);

    }
}
