<?php

namespace App\Entity;
use App\Controller\uploader;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\Common\Persistence\ManagerRegistry;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * Offre
 *
 * @ORM\Table(name="offre")
 * @ORM\Entity
 * @Vich\Uploadable
 */
class Offre
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_offre", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     *  @Groups("post:read")
     */
    private $idOffre;

    /**
     * @var string
     * @Assert\NotBlank(message="description  doit etre non vide")
     *  @Groups("post:read")
     * @Assert\Length(
     *      min = 7,
     *      max = 100,
     *      minMessage = "doit etre >=7 ",
     *      maxMessage = "doit etre <=15" )
     *
     * @ORM\Column(name="text_offre", type="string", length=100, nullable=false)
     */
    private $textOffre;

    /**
     * @var \DateTime
     * @Groups("post:read")
     * @Assert\NotBlank(message="description  doit etre non vide")
     *
     * @ORM\Column(name="date_offre", type="date", nullable=false)
     */
    private $dateOffre;

    /**
     * @var string
     * @Assert\NotBlank(message="description  doit etre non vide")
     * @Groups("post:read")
     * @Assert\Length(
     *      min = 7,
     *      max = 100,
     *      minMessage = "doit etre >=7 ",
     *      maxMessage = "doit etre <=15" )
     *
     * @ORM\Column(name="type_offre", type="string", length=60, nullable=false)
     */
    private $typeOffre;

    /**
     * @var string
     * @Assert\NotBlank(message="description  doit etre non vide")
     * @Groups("post:read")
     * @Assert\Length(
     *      min = 7,
     *      max = 100,
     *      minMessage = "doit etre >=7 ",
     *      maxMessage = "doit etre <=15" )
     *
     *
     * @ORM\Column(name="img_offre", type="string", length=100, nullable=false)
     */
    private $imgOffre;


    /**
     * @return int
     */
    public function getIdOffre(): int
    {
        return $this->idOffre;
    }

    /**
     * @param int $idOffre
     * @return Offre
     */
    public function setIdOffre(int $idOffre): Offre
    {
        $this->idOffre = $idOffre;
        return $this;
    }

    /**
     * @return string
     */
    public function getTextOffre(): ?string
    {
        return $this->textOffre;
    }

    /**
     * @param string $textOffre
     * @return Offre
     */
    public function setTextOffre(string $textOffre): Offre
    {
        $this->textOffre = $textOffre;
        return $this;
    }

    /**
     * @return \DateTime
     */
    public function getDateOffre(): ?\DateTime
    {
        return $this->dateOffre;
    }

    /**
     * @param \DateTime $dateOffre
     * @return Offre
     */
    public function setDateOffre(\DateTime $dateOffre): Offre
    {
        $this->dateOffre = $dateOffre;
        return $this;
    }

    /**
     * @return string
     */
    public function getTypeOffre(): ?string
    {
        return $this->typeOffre;
    }

    /**
     * @param string $typeOffre
     * @return Offre
     */
    public function setTypeOffre(string $typeOffre): Offre
    {
        $this->typeOffre = $typeOffre;
        return $this;
    }

    /**
     * @return string|null
     */
    public function getImgOffre(): ?string
    {
        return $this->imgOffre;
    }

    /**
     * @param string|null $imgOffre
     * @return $this
     */
    public function setImgOffre(string $imgOffre): self
    {
        $this->imgOffre = $imgOffre;
        return $this;
    }

    public function getImageFilename(): ?string
    {
        return $this->imgOffre;
    }
    public function setImageFilename(?string $imageFilename): self
    {
        $this->imgOffre = $imageFilename;
        return $this;
    }
    public function getImagePath()
    {
        return uploader::PROFILE_IMAGE.'/'.$this->getImageFilename();
    }

    public function __call(string $name, array $arguments)
    {
        // TODO: Implement __call() method.
    }



}
