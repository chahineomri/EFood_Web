<?php

namespace App\Controller;

use App\Entity\Commande;
use App\Entity\Produit;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;

class PanierController extends AbstractController
{
    /**
     * @Route("/panier", name="app_panier")
     */
    public function index(): Response
    {
        return $this->render('panier/index.html.twig', [
            'controller_name' => 'PanierController',
        ]);
    }

    /**
     * @Route("/produits", name="list_produits")
     */
    public function listProduct():Response
    {
        $em=$this->getDoctrine()->getManager();
        $listProducts= $em->getRepository(Produit::class)->findAll();
        return $this->render("listProduit.html.twig",['listP'=>$listProducts]);
    }

    /**
     * @Route ("", name="cart_add")
     */
    public function addProductToPanier():Response
    {


    }

}
