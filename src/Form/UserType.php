<?php

namespace App\Form;

use App\Entity\User;
use Symfony\Component\Validator\Constraints\Image;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
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

class UserType extends AbstractType
{

    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $user = $options['data'] ?? null;
        $isEdit = $user && $user->getId();
        $builder
            ->add('nameuser',TextType::class,['attr' => ['placeholder' => 'Enter your name']])
            ->add('lastnameuser',TextType::class,['attr' => ['placeholder' => 'Enter your last name']])
            ->add('emailuser',EmailType::class,array(
                'attr' => ['placeholder' => 'Enter your email '],
                'constraints' => [
                  new NotBlank([
                      'allowNull' => false,
                      'message' => 'Please enter your email!'
                  ]),
                ])
            );
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
                    ]),
                    'required' => true,
                    ],
                'type' => PasswordType::class,
                    'first_options' => array(
                    'attr' => ['placeholder' => 'Password '],
                    'label' =>'Password '),
                    'second_options' => array(
                    'attr' => ['placeholder' => 'Password Verification'],
                    'label' => 'Password Verification '),
            ))
            ->add('userrole',ChoiceType::class, [
                'required' => true,
                'multiple' => false,
                'expanded' => false,
                'choices'  => [
                    'Client' => 'ROLE_USER',
                ],
        ])
            ->add('userstatus',ChoiceType::class, [
            'choices'  => [
            'active account' => true,
        ],
        ])
        ->add('agreeTerms', CheckboxType::class,[
        'mapped' => false,
            'data' => true,
            'constraints' => [
        new IsTrue([
            'message' => 'You must agree to our terms.'
            ])
        ],])
        ;

        // Data transformer
        $builder->get('userrole')
            ->addModelTransformer(new CallbackTransformer(
                function ($userroleArray) {
                    // transform the array to a string
                    return  is_array($userroleArray) ? count($userroleArray) : null ;
                },
                function ($userroleString) {
                    // transform the string back to an array
                    return [$userroleString];
                }
            ));


    }
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => User::class,
        ));
    }

}
