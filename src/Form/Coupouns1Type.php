<?php

namespace App\Form;

use App\Entity\Coupouns;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class Coupouns1Type extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('textCoupouns')
            ->add('codeCoupouns')
            ->add('date1')
            ->add('date2')
            ->add('idPromo1')
            ->add('dureeCoupouns')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Coupouns::class,
        ]);
    }
}
