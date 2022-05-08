<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Promo
 *
 * @ORM\Table(name="promo")
 * @ORM\Entity
 */
class Promo
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_promo", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idPromo;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_promo", type="string", length=50, nullable=false)
     */
    private $nomPromo;

    /**
     * @var int
     *
     * @ORM\Column(name="type_promo", type="integer", nullable=false)
     */
    private $typePromo;

    /**
     * @var string
     *
     * @ORM\Column(name="date_d", type="string", length=100, nullable=false)
     */
    private $dateD;

    /**
     * @var string
     *
     * @ORM\Column(name="date_f", type="string", length=100, nullable=false)
     */
    private $dateF;

    /**
     * @var int
     *
     * @ORM\Column(name="id_resto", type="integer", nullable=false)
     */
    private $idResto;

    /**
     * @var string
     *
     * @ORM\Column(name="text_promo", type="string", length=100, nullable=false)
     */
    private $textPromo;

    public function getIdPromo(): ?int
    {
        return $this->idPromo;
    }

    public function getNomPromo(): ?string
    {
        return $this->nomPromo;
    }

    public function setNomPromo(string $nomPromo): self
    {
        $this->nomPromo = $nomPromo;

        return $this;
    }

    public function getTypePromo(): ?int
    {
        return $this->typePromo;
    }

    public function setTypePromo(int $typePromo): self
    {
        $this->typePromo = $typePromo;

        return $this;
    }

    public function getDateD(): ?string
    {
        return $this->dateD;
    }

    public function setDateD(string $dateD): self
    {
        $this->dateD = $dateD;

        return $this;
    }

    public function getDateF(): ?string
    {
        return $this->dateF;
    }

    public function setDateF(string $dateF): self
    {
        $this->dateF = $dateF;

        return $this;
    }

    public function getIdResto(): ?int
    {
        return $this->idResto;
    }

    public function setIdResto(int $idResto): self
    {
        $this->idResto = $idResto;

        return $this;
    }

    public function getTextPromo(): ?string
    {
        return $this->textPromo;
    }

    public function setTextPromo(string $textPromo): self
    {
        $this->textPromo = $textPromo;

        return $this;
    }


}
