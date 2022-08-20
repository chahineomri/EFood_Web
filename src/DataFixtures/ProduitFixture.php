<?php

namespace App\DataFixtures;

use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Persistence\ObjectManager;
use App\Entity\Produit;

class ProduitFixture extends Fixture
{
    public function load(ObjectManager $manager): void
    {
        $produit1 = new Produit();
        $produit1->setName("Fraise")
            ->setImage("product-img-1.jpg")
            ->setPrice(85)
            ->setCategory("Fruits");

        $produit2 = new Produit();
        $produit2->setName("Berry")
            ->setImage("product-img-2.jpg")
            ->setPrice(70)
            ->setCategory("Fruits");


        $produit3 = new Produit();
        $produit3->setName("Lemon")
            ->setImage("product-img-3.jpg")
            ->setPrice(35)
            ->setCategory("Fruits");

        $produit4 = new Produit();
        $produit4->setName("Kiwi")
            ->setImage("product-img-4.jpg")
            ->setPrice(10)
            ->setCategory("Fruits");

        $produit5 = new Produit();
        $produit5->setName("Apple")
            ->setImage("product-img-5.jpg")
            ->setPrice(6)
            ->setCategory("Fruits");

        $produit6 = new Produit();
        $produit6->setName("Strawberry")
            ->setImage("product-img-6.jpg")
            ->setPrice(12)
            ->setCategory("Fruits");

        $manager->persist($produit1);
        $manager->persist($produit2);
        $manager->persist($produit3);
        $manager->persist($produit4);
        $manager->persist($produit5);
        $manager->persist($produit6);

        $manager->flush();
    }
}
