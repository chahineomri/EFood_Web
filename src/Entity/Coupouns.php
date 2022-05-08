<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Coupouns
 *
 * @ORM\Table(name="coupouns")
 * @ORM\Entity
 */
class Coupouns
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_coupouns", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idCoupouns;

    /**
     * @var string
     *
     * @ORM\Column(name="text_coupouns", type="string", length=200, nullable=false)
     */
    private $textCoupouns;

    /**
     * @var string
     *
     * @ORM\Column(name="code_coupouns", type="string", length=50, nullable=false)
     */
    private $codeCoupouns;

    /**
     * @var string
     *
     * @ORM\Column(name="date_1", type="string", length=50, nullable=false)
     */
    private $date1;

    /**
     * @var string
     *
     * @ORM\Column(name="date_2", type="string", length=50, nullable=false)
     */
    private $date2;

    /**
     * @var int
     *
     * @ORM\Column(name="id_promo1", type="integer", nullable=false)
     */
    private $idPromo1;

    /**
     * @var int
     *
     * @ORM\Column(name="duree_coupouns", type="integer", nullable=false)
     */
    private $dureeCoupouns;

    public function getIdCoupouns(): ?int
    {
        return $this->idCoupouns;
    }

    public function getTextCoupouns(): ?string
    {
        return $this->textCoupouns;
    }

    public function setTextCoupouns(string $textCoupouns): self
    {
        $this->textCoupouns = $textCoupouns;

        return $this;
    }

    public function getCodeCoupouns(): ?string
    {
        return $this->codeCoupouns;
    }

    public function setCodeCoupouns(string $codeCoupouns): self
    {
        $this->codeCoupouns = $codeCoupouns;

        return $this;
    }

    public function getDate1(): ?string
    {
        return $this->date1;
    }

    public function setDate1(string $date1): self
    {
        $this->date1 = $date1;

        return $this;
    }

    public function getDate2(): ?string
    {
        return $this->date2;
    }

    public function setDate2(string $date2): self
    {
        $this->date2 = $date2;

        return $this;
    }

    public function getIdPromo1(): ?int
    {
        return $this->idPromo1;
    }

    public function setIdPromo1(int $idPromo1): self
    {
        $this->idPromo1 = $idPromo1;

        return $this;
    }

    public function getDureeCoupouns(): ?int
    {
        return $this->dureeCoupouns;
    }

    public function setDureeCoupouns(int $dureeCoupouns): self
    {
        $this->dureeCoupouns = $dureeCoupouns;

        return $this;
    }


}
