<?php

namespace App\Controller;

use App\Data\SearchData;
use App\Entity\Produit;
use App\Form\searchForm;
use App\Repository\ProduitRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ProduitController extends AbstractController
{
    /**
     * @Route("/produit", name="app_produit")
     */
    public function index(Request $request, PaginatorInterface $paginator, ProduitRepository $produitRepository): Response
    {

        $data= new SearchData();
        $form = $this->createForm(searchForm::class,$data);
        $form->handleRequest($request);

        $produits = $produitRepository->findSearch($data);
        $produits = $paginator->paginate($produits,
        $request->query->getInt('page',1),
        3);

        return $this->render('produit/index.html.twig', [
            'produits' => $produits,
            'form'=>$form->createView()
        ]);
    }
}
