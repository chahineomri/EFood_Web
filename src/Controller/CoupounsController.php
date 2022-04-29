<?php

namespace App\Controller;

use App\Entity\Coupouns;
use App\Form\Coupouns1Type;
use App\Services\PdfService;
use App\Services\QrcodeService;
use App\Service\MailerService;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;



/**
 * @Route("/coupouns")
 */
class CoupounsController extends AbstractController
{
    /**
     * @Route("/", name="app_coupouns_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
        $coupouns = $entityManager
            ->getRepository(Coupouns::class)
            ->findAll();

        return $this->render('coupouns/index.html.twig', [
            'coupouns' => $coupouns,
        ]);
    }

    /**
     * @Route("/try", name="app_coupouns_indexfront", methods={"GET"})
     */
    public function indexfront(EntityManagerInterface $entityManager): Response
    {
        $coupouns = $entityManager
            ->getRepository(Coupouns::class)
            ->findAll();

        return $this->render('coupouns/indexfront.html.twig', [
            'coupouns' => $coupouns,
        ]);
    }

    /**
     * @Route("/new", name="app_coupouns_new", methods={"GET", "POST"})
     * @param QrcodeService $qrcodeService
     */
    public function new(Request $request, EntityManagerInterface $entityManager, QrcodeService $qrcodeService): Response
    {
        $qrCode = null;

        $coupoun = new Coupouns();
        $form = $this->createForm(Coupouns1Type::class, $coupoun);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($coupoun);
            $entityManager->flush();
            $coupoun = $form->getData();
            $qrCode = $qrcodeService->qrcode($coupoun, $form['codeCoupouns']->getData());

            return $this->redirectToRoute('app_coupouns_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('coupouns/new.html.twig', [
            'coupoun' => $coupoun,
            'form' => $form->createView(),
            'qrCode' => $qrCode
        ]);
    }

    /**
     * @Route("/{idCoupouns}", name="app_coupouns_show", methods={"GET"})
     */
    public function show(Coupouns $coupoun): Response
    {
        return $this->render('coupouns/show.html.twig', [
            'coupoun' => $coupoun,
        ]);
    }

    /**
     * @Route("/{idCoupouns}/edit", name="app_coupouns_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Coupouns $coupoun, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(Coupouns1Type::class, $coupoun);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_coupouns_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('coupouns/edit.html.twig', [
            'coupoun' => $coupoun,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idCoupouns}", name="app_coupouns_delete", methods={"POST"})
     */
    public function delete(Request $request, Coupouns $coupoun, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete' . $coupoun->getIdCoupouns(), $request->request->get('_token'))) {
            $entityManager->remove($coupoun);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_coupouns_index', [], Response::HTTP_SEE_OTHER);
    }

     /**
      * @Route("/{idCoupouns}/pdf", name="app_coupouns_pdf" , methods={"GET"})
      *
      */
    public function generatePdfPersonne( Coupouns $coupoun= null, PdfService $pdf) {

        $html = $this->render('coupouns/pdf.html.twig', ['coupoun' => $coupoun]);
        $pdf->showPdfFile($html);
        return $this->redirectToRoute('app_coupouns_index', [], Response::HTTP_SEE_OTHER);
    }
}


