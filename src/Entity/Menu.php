<?php

namespace App\Entity;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;

/**
 * Menu
 *
 * @ORM\Table(name="menu")
 * @ORM\Entity(repositoryClass="App\Repository\MenuRepository")
 */
class Menu
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
     * @Assert\Length(
     *      min = 2,
     *      max = 30,
     *      minMessage = " Minimum {{ limit }} caracteres",
     *      maxMessage = " Maximum {{ limit }} caracteres"
     * )
     *@Assert\NotBlank(message="champ obligatoire")
     * @ORM\Column(name="nom", type="string", length=256, nullable=false)
     */
    private $nom;

    /**
     * @var int
     *@Assert\NotBlank(message="champ obligatoire")
     * @ORM\Column(name="quantite", type="integer", nullable=false)
     */
    private $quantite;

    /**
     * @var int
     * @Assert\Positive
     *@Assert\NotBlank(message="champ obligatoire")
     * @ORM\Column(name="prix", type="integer", nullable=false)
     */
    private $prix;

    /**
     * @var string
     * @Assert\Length(
     *      min = 2,
     *      max = 30,
     *      minMessage = " Minimum {{ limit }} caracteres",
     *      maxMessage = " Maximum {{ limit }} caracteres"
     * )
     * @ORM\Column(name="imgSrc", type="blob", length=0)
     */
    private $imgsrc;

    /**
     * @var string
     *@Assert\Length(
     *      min = 2,
     *      max = 30,
     *      minMessage = " Minimum {{ limit }} caracteres",
     *      maxMessage = " Maximum {{ limit }} caracteres"
     * )
     * @Assert\NotBlank(message="champ obligatoire")
     * @ORM\Column(name="saison", type="string", length=256, nullable=false)
     */
    private $saison;

    /**
     * @var \DateTime
     *@Assert\NotBlank(message="champ obligatoire")
     * @ORM\Column(name="date_mise_en_rayon", type="date", nullable=false)
     */
    private $dateMiseEnRayon;

    /**
     * @var \DateTime
     *@Assert\NotBlank(message="champ obligatoire")
     * @ORM\Column(name="date_peremption", type="date", nullable=false)
     */
    private $datePeremption;

    /**
     * @var int
     *@Assert\Positive
     * @ORM\Column(name="id_restaurant", type="integer", nullable=false)
     */
    private $idRestaurant;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $likeMenu;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $dislikeMenu;

    /**
     * @ORM\OneToMany(targetEntity=Note::class, mappedBy="Menu")
     */
    private $notes;

    public function __construct()
    {
        $this->notes = new ArrayCollection();
    }

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

    public function getQuantite(): ?int
    {
        return $this->quantite;
    }

    public function setQuantite(int $quantite): self
    {
        $this->quantite = $quantite;

        return $this;
    }

    public function getPrix(): ?int
    {
        return $this->prix;
    }

    public function setPrix(int $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getImgsrc()
    {
        return $this->imgsrc;
    }

    public function setImgsrc($imgsrc): self
    {
        $this->imgsrc = $imgsrc;

        return $this;
    }

    public function getSaison(): ?string
    {
        return $this->saison;
    }

    public function setSaison(string $saison): self
    {
        $this->saison = $saison;

        return $this;
    }

    public function getDateMiseEnRayon(): ?\DateTimeInterface
    {
        return $this->dateMiseEnRayon;
    }

    public function setDateMiseEnRayon(\DateTimeInterface $dateMiseEnRayon): self
    {
        $this->dateMiseEnRayon = $dateMiseEnRayon;

        return $this;
    }

    public function getDatePeremption(): ?\DateTimeInterface
    {
        return $this->datePeremption;
    }

    public function setDatePeremption(\DateTimeInterface $datePeremption): self
    {
        $this->datePeremption = $datePeremption;

        return $this;
    }

    public function getIdRestaurant(): ?int
    {
        return $this->idRestaurant;
    }

    public function setIdRestaurant(int $idRestaurant): self
    {
        $this->idRestaurant = $idRestaurant;

        return $this;
    }

    public function getLikeMenu(): ?int
    {
        return $this->likeMenu;
    }

    public function setLikeMenu(?int $likeMenu): self
    {
        $this->likeMenu = $likeMenu;

        return $this;
    }

    public function getDislikeMenu(): ?int
    {
        return $this->dislikeMenu;
    }

    public function setDislikeMenu(?int $dislikeMenu): self
    {
        $this->dislikeMenu = $dislikeMenu;

        return $this;
    }

    /**
     * @return Collection<int, Note>
     */
    public function getNotes(): Collection
    {
        return $this->notes;
    }

    public function addNote(Note $note): self
    {
        if (!$this->notes->contains($note)) {
            $this->notes[] = $note;
            $note->setMenu($this);
        }

        return $this;
    }

    public function removeNote(Note $note): self
    {
        if ($this->notes->removeElement($note)) {
            // set the owning side to null (unless already changed)
            if ($note->getMenu() === $this) {
                $note->setMenu(null);
            }
        }

        return $this;
    }


}
