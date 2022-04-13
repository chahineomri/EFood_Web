<?php

namespace App\Form;

use App\Entity\User;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\RepeatedType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Image;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Validator\Constraints\NotNull;

class UserTypeUpdateType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {

        $user = $options['data'] ?? null;
        $isEdit = $user && $user->getId();
        $builder
            ->add('nameuser',TextType::class,['attr' => ['placeholder' => 'Enter your name']])
            ->add('lastnameuser',TextType::class,['attr' => ['placeholder' => 'Enter your last name']]);
        $imageConstraints= [
            new Image([
                'maxSize' => '5M'
            ])];
        if (!$isEdit ||!$user->getImageFilename()) {
            $imageConstraints[] = new NotNull([
                'message' => 'Please upload an image',
            ]);
        }
        $builder
            ->add('profilepicuser', FileType::class, [
                'required' => false,
                'constraints' => $imageConstraints,
                'label'=> 'Profile Picture',
                'data_class' => null
            ])

            ->add('plainPassword',RepeatedType::class,array (
                'invalid_message' => 'The password fields must match.',
                'mapped' => false,
                'constraints' => [
                    new NotBlank([
                        'allowNull' => false,
                        'message' => 'Please enter your password!'
                    ]),
                    new Length([
                        'min' => 8,
                        'minMessage' => 'Come on, you can think of a password longer than that!'
                    ])
                ],
                'type' => PasswordType::class,
                'first_options' => array(
                    'attr' => ['placeholder' => 'Password '],
                    'label' =>'Password '),
                'second_options' => array(
                    'attr' => ['placeholder' => 'Password Verification'],
                    'label' => 'Password Verification '),
            ));

    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => User::class,
        ]);
    }
}
