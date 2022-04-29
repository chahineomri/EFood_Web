<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

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
     * @Assert\NotBlank(message="description  doit etre non vide")
     * @Assert\Length(
     *      min = 7,
     *      max = 100,
     *      minMessage = "doit etre >=7 ",
     *      maxMessage = "doit etre <=15" )
     *
     * @ORM\Column(name="nom_promo", type="string", length=50, nullable=false)
     */
    private $nomPromo;

    /**
     * @var int
     * @Assert\NotBlank(message="description  doit etre non vide")
     *
     * @ORM\Column(name="type_promo", type="integer", nullable=false)
     */
    private $typePromo;

    /**
     * @var \DateTime
     * @Assert\NotBlank(message="description  doit etre non vide")
     *
     * @ORM\Column(name="date_D", type="date", nullable=false)
     */
    private $dateD;

    /**
     * @var \DateTime
     * @Assert\NotBlank(message="description  doit etre non vide")
     *
     * @ORM\Column(name="date_F", type="date", nullable=false)
     */
    private $dateF;

    /**
     * @var int
     * @Assert\NotBlank(message="description  doit etre non vide")
     * @Assert\Length(
     *      min = 7,
     *      max = 100,
     *      minMessage = "doit etre >=7 ",
     *      maxMessage = "doit etre <=15" )
     *
     * @ORM\Column(name="id_resto", type="integer", nullable=false)
     */
    private $idResto;

    /**
     * @var string
     * @Assert\NotBlank(message="description  doit etre non vide")
     * @Assert\Length(
     *      min = 7,
     *      max = 100,
     *      minMessage = "doit etre >=7 ",
     *      maxMessage = "doit etre <=15" )
     *
     * @ORM\Column(name="text_promo", type="string", length=100, nullable=false)
     */
    private $textPromo;

    /**
     * @return int
     */
    public function getIdPromo(): ?int
    {
        return $this->idPromo;
    }

    /**
     * @param int $idPromo
     * @return Promo
     */
    public function setIdPromo(int $idPromo): Promo
    {
        $this->idPromo = $idPromo;
        return $this;
    }

    /**
     * @return string
     */
    public function getNomPromo(): ?string
    {
        return $this->nomPromo;
    }

    /**
     * @param string $nomPromo
     * @return Promo
     */
    public function setNomPromo(string $nomPromo): Promo
    {
        $this->nomPromo = $nomPromo;
        return $this;
    }

    /**
     * @return int
     */
    public function getTypePromo(): ?int
    {
        return $this->typePromo;
    }

    /**
     * @param int $typePromo
     * @return Promo
     */
    public function setTypePromo(int $typePromo): Promo
    {
        $this->typePromo = $typePromo;
        return $this;
    }

    /**
     * @return \DateTime
     */
    public function getDateD(): ?\DateTime
    {
        return $this->dateD;
    }

    /**
     * @@param \DateTime $dateD
     * @return Promo
     */
    public function setDateD(\DateTime $dateD): Promo
    {
        $this->dateD = $dateD;
        return $this;
    }

    /**
     * @return \DateTime
     */
    public function getDateF(): ?\DateTime
    {
        return $this->dateF;
    }

    /**
    *@param \DateTime $dateF
     * @return Promo
     */
    public function setDateF(\DateTime $dateF): Promo
    {
        $this->dateF = $dateF;
        return $this;
    }

    /**
     * @return int
     */
    public function getIdResto(): ?int
    {
        return $this->idResto;
    }

    /**
     * @param int $idResto
     * @return Promo
     */
    public function setIdResto(int $idResto): Promo
    {
        $this->idResto = $idResto;
        return $this;
    }

    /**
     * @return string
     */
    public function getTextPromo(): ?string
    {
        return $this->textPromo;
    }

    /**
     * @param string $textPromo
     * @return Promo
     */
    public function setTextPromo(string $textPromo): Promo
    {
        $this->textPromo = $textPromo;
        return $this;
    }


}
