<?php

namespace App\Form;

use App\Entity\User;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\RepeatedType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\NotBlank;

class UserPasswordChangeType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {

        $builder
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
                    ]),
                    'required' => true,
                ],
                'type' => PasswordType::class,
                'first_options' => array(
                    'attr' => ['placeholder' => 'New Password '],
                    'label' =>'Password '),
                'second_options' => array(
                    'attr' => ['placeholder' => 'Password Verification'],
                    'label' => 'Verify Password '),
            ));
        $builder
            ->add('oldPassword',PasswordType::class,[
                'attr' => ['placeholder' => 'Enter your password to update'],
                'constraints' => [
                    new NotBlank([
                        'allowNull' => false,
                        'message' => 'Please enter your current password!'
                    ]),
                    ],
                'mapped' => false,
            ]);
    }
    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => User::class,
        ]);
    }
}
