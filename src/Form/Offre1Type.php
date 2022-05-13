<?php

namespace App\Form;

use App\Entity\Offre;
use Symfony\Component\Validator\Constraints\Image;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\RepeatedType;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\IsTrue;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\NotNull;
use Symfony\Component\Validator\Constraints\Unique;
use Symfony\Component\Form\CallbackTransformer;



class Offre1Type extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $offre= $options['data'] ?? null;
        $isEdit = $offre;
        $builder
            ->add('textOffre',TextType::class,['attr' => ['placeholder' => 'Enter the name of the offer']])
            ->add('dateOffre')
            ->add('typeOffre',TextType::class,['attr' => ['placeholder' => 'Enter the type of the the offer']]);
              $imageConstraints= [
                  new Image([
                      'maxSize' => '5M'
                  ])];
        if (!$isEdit ||!$offre->getImageFilename()) {
            $imageConstraints[] = new NotNull([
                'message' => 'Please upload an image',
            ]);
        }
        $builder
            ->add('imgOffre', FileType::class, [
                'required' => false,
                'constraints' => $imageConstraints,
                'label'=> 'Image Offer',
                'data_class' => null
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Offre::class,
        ]);
    }
}
