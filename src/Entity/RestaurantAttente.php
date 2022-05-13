<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * RestaurantAttente
 *
 * @ORM\Table(name="restaurant_attente")
 * @ORM\Entity(repositoryClass="App\Repository\RestaurantAttenteRepository")
 */
class RestaurantAttente
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=256, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="position", type="string", length=256, nullable=false)
     */
    private $position;

    /**
     * @var string
     *
     * @ORM\Column(name="gerant_restaurant", type="string", length=256, nullable=false)
     */
    private $gerantRestaurant;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=256, nullable=false)
     */
    private $email;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPosition(): ?string
    {
        return $this->position;
    }

    public function setPosition(string $position): self
    {
        $this->position = $position;

        return $this;
    }

    public function getGerantRestaurant(): ?string
    {
        return $this->gerantRestaurant;
    }

    public function setGerantRestaurant(string $gerantRestaurant): self
    {
        $this->gerantRestaurant = $gerantRestaurant;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }


}
