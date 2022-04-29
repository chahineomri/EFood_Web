<?php

namespace App\Controller;

use App\Entity\Promo;
use App\Form\Promo1Type;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/promo")
 */
class PromoController extends AbstractController
{
    /**
     * @Route("/", name="app_promo_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
        $promos = $entityManager
            ->getRepository(Promo::class)
            ->findAll();

        return $this->render('promo/index.html.twig', [
            'promos' => $promos,
        ]);
    }
    /**
     * @Route("/try", name="app_promo_indexfront", methods={"GET"})
     */

    public function indexfront(EntityManagerInterface $entityManager): Response
    {
        $promos = $entityManager
            ->getRepository(Promo::class)
            ->findAll();

        return $this->render('promo/indexfront.html.twig', [
            'promos' => $promos,
        ]);
    }


    /**
     * @Route("/new", name="app_promo_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $promo = new Promo();
        $form = $this->createForm(Promo1Type::class, $promo);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($promo);
            $entityManager->flush();

            return $this->redirectToRoute('app_promo_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('promo/new.html.twig', [
            'promo' => $promo,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idPromo}", name="app_promo_show", methods={"GET"})
     */
    public function show(Promo $promo): Response
    {
        return $this->render('promo/show.html.twig', [
            'promo' => $promo,
        ]);
    }

    /**
     * @Route("/{idPromo}/edit", name="app_promo_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Promo $promo, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(Promo1Type::class, $promo);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_promo_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('promo/edit.html.twig', [
            'promo' => $promo,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idPromo}", name="app_promo_delete", methods={"POST"})
     */
    public function delete(Request $request, Promo $promo, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$promo->getIdPromo(), $request->request->get('_token'))) {
            $entityManager->remove($promo);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_promo_index', [], Response::HTTP_SEE_OTHER);
    }
}
