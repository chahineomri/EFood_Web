<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commande
 *
 * @ORM\Table(name="commande")
 * @ORM\Entity
 */
class Commande
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_commande", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idCommande;

    /**
     * @var int
     *
     * @ORM\Column(name="id_client", type="integer", nullable=false)
     */
    private $idClient;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_client", type="string", length=256, nullable=false)
     */
    private $nomClient;

    /**
     * @var string
     *
     * @ORM\Column(name="plat_cmd", type="string", length=256, nullable=false)
     */
    private $platCmd;

    /**
     * @var string
     *
     * @ORM\Column(name="lieu_cmd", type="string", length=256, nullable=false)
     */
    private $lieuCmd;

    public function getIdCommande(): ?int
    {
        return $this->idCommande;
    }

    public function getIdClient(): ?int
    {
        return $this->idClient;
    }

    public function setIdClient(int $idClient): self
    {
        $this->idClient = $idClient;

        return $this;
    }

    public function getNomClient(): ?string
    {
        return $this->nomClient;
    }

    public function setNomClient(string $nomClient): self
    {
        $this->nomClient = $nomClient;

        return $this;
    }

    public function getPlatCmd(): ?string
    {
        return $this->platCmd;
    }

    public function setPlatCmd(string $platCmd): self
    {
        $this->platCmd = $platCmd;

        return $this;
    }

    public function getLieuCmd(): ?string
    {
        return $this->lieuCmd;
    }

    public function setLieuCmd(string $lieuCmd): self
    {
        $this->lieuCmd = $lieuCmd;

        return $this;
    }


}
