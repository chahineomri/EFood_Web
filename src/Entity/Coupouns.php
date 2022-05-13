<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
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
     * @Groups("post:read")
     */
    private $idCoupouns;

    /**
     * @return int
     */
    public function getIdCoupouns(): int
    {
        return $this->idCoupouns;
    }

    /**
     * @param int $idCoupouns
     * @return Coupouns
     */
    public function setIdCoupouns(int $idCoupouns): Coupouns
    {
        $this->idCoupouns = $idCoupouns;
        return $this;
    }

    /**
     * @var string
     * @Assert\NotBlank(message="description  doit etre non vide")
     * @Assert\Length(
     *      min = 7,
     *      max = 100,
     *      minMessage = "doit etre >=7 ",
     *      maxMessage = "doit etre <=15" )
     *
     * @ORM\Column(name="text_coupouns", type="string", length=200, nullable=false)
     *  @Groups("post:read")
     */
    private $textCoupouns;

    /**
     * @var string
     * @Assert\NotBlank(message="description  doit etre non vide")
     * @Assert\Length(
     *      min = 7,
     *      max = 100,
     *      minMessage = "doit etre >=7 ",
     *      maxMessage = "doit etre <=15" )
     *
     * @ORM\Column(name="code_coupouns", type="string", length=200, nullable=false)
     *  @Groups("post:read")
     */
    private $codeCoupouns;

    /**
     * @var \DateTime
     * @Assert\NotBlank(message="description  doit etre non vide")
     *
     * @ORM\Column(name="date_1", type="date", nullable=false)
     *  @Groups("post:read")
     */
    private $date1;

    /**
     * @var \DateTime
     * @Assert\NotBlank(message="description  doit etre non vide")
     *
     * @ORM\Column(name="date_2", type="date", nullable=false)
     *  @Groups("post:read")
     */
    private $date2;

    /**
     * @var int
     * @Assert\NotBlank(message="description  doit etre non vide")
     *
     * @ORM\Column(name="id_promo1", type="integer", nullable=false)
     *  @Groups("post:read")
     */
    private $idPromo1;

    /**
     * @var int
     * @Assert\NotBlank(message="description  doit etre non vide")
     * @ORM\Column(name="duree_coupouns", type="integer", nullable=false)
     *  @Groups("post:read")
     */
    private $dureeCoupouns;

    /**
     * @return string
     */
    public function getTextCoupouns(): ?string
    {
        return $this->textCoupouns;
    }

    /**
     * @param string $textCoupouns
     * @return Coupouns
     */
    public function setTextCoupouns(string $textCoupouns): Coupouns
    {
        $this->textCoupouns = $textCoupouns;
        return $this;
    }

    /**
     * @return string
     */
    public function getCodeCoupouns(): ?string
    {
        return $this->codeCoupouns;
    }
    public function __toString() {
        return $this->codeCoupouns;
    }
    /**
     * @param string $codeCoupouns
     * @return Coupouns
     */
    public function setCodeCoupouns(string $codeCoupouns): Coupouns
    {
        $this->codeCoupouns = $codeCoupouns;
        return $this;
    }

    /**
     * @return \DateTime
     */
    public function getDate1(): ?\DateTime
    {
        return $this->date1;
    }

    /**
     * @param \DateTime $date1
     * @return Coupouns
     */
    public function setDate1(\DateTime $date1): Coupouns
    {
        $this->date1 = $date1;
        return $this;
    }

    /**
     * @return \DateTime
     */
    public function getDate2(): ?\DateTime
    {
        return $this->date2;
    }

    /**
     * @param \DateTime $date2
     * @return Coupouns
     */
    public function setDate2(\DateTime $date2): Coupouns
    {
        $this->date2 = $date2;
        return $this;
    }

    /**
     * @return int
     */
    public function getIdPromo1(): ?int
    {
        return $this->idPromo1;
    }

    /**
     * @param int $idPromo1
     * @return Coupouns
     */
    public function setIdPromo1(int $idPromo1): Coupouns
    {
        $this->idPromo1 = $idPromo1;
        return $this;
    }

    /**
     * @return int
     */
    public function getDureeCoupouns(): ?int
    {
        return $this->dureeCoupouns;
    }

    /**
     * @param int $dureeCoupouns
     * @return Coupouns
     */
    public function setDureeCoupouns(int $dureeCoupouns): Coupouns
    {
        $this->dureeCoupouns = $dureeCoupouns;
        return $this;
    }



}
