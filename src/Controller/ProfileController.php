<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Psr\Log\LoggerInterface;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @IsGranted("ROLE_USER")
 */
class ProfileController extends AbstractController
{
    /**
     * @Route("/profile", name="app_profile")
     */
    public function index(LoggerInterface $logger): Response
    {
        $logger->debug('Checking account page for '.$this->getUser()->getEmailuser());
        return $this->render('user/profile.html.twig', [
            'controller_name' => 'ProfileController',
        ]);
    }
}
