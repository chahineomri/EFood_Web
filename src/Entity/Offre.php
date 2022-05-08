<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Offre
 *
 * @ORM\Table(name="offre")
 * @ORM\Entity
 */
class Offre
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_offre", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idOffre;

    /**
     * @var string
     *
     * @ORM\Column(name="text_offre", type="string", length=100, nullable=false)
     */
    private $textOffre;

    /**
     * @var string
     *
     * @ORM\Column(name="date_offre", type="string", length=60, nullable=false)
     */
    private $dateOffre;

    /**
     * @var string
     *
     * @ORM\Column(name="type_offre", type="string", length=60, nullable=false)
     */
    private $typeOffre;

    /**
     * @var string
     *
     * @ORM\Column(name="img_offre", type="string", length=200, nullable=false)
     */
    private $imgOffre;

    public function getIdOffre(): ?int
    {
        return $this->idOffre;
    }

    public function getTextOffre(): ?string
    {
        return $this->textOffre;
    }

    public function setTextOffre(string $textOffre): self
    {
        $this->textOffre = $textOffre;

        return $this;
    }

    public function getDateOffre(): ?string
    {
        return $this->dateOffre;
    }

    public function setDateOffre(string $dateOffre): self
    {
        $this->dateOffre = $dateOffre;

        return $this;
    }

    public function getTypeOffre(): ?string
    {
        return $this->typeOffre;
    }

    public function setTypeOffre(string $typeOffre): self
    {
        $this->typeOffre = $typeOffre;

        return $this;
    }

    public function getImgOffre(): ?string
    {
        return $this->imgOffre;
    }

    public function setImgOffre(string $imgOffre): self
    {
        $this->imgOffre = $imgOffre;

        return $this;
    }


}
