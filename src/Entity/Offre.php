<?php

namespace App\Entity;
use App\Controller\uploader;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\Common\Persistence\ManagerRegistry;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
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
     */
    private $idOffre;

    /**
     * @var string
     * @Assert\NotBlank(message="description  doit etre non vide")
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
     * @Assert\NotBlank(message="description  doit etre non vide")
     *
     * @ORM\Column(name="date_offre", type="date", nullable=false)
     */
    private $dateOffre;

    /**
     * @var string
     * @Assert\NotBlank(message="description  doit etre non vide")
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
     * @Vich\UploadableField(mapping="offre_images", fileNameProperty="imgOffre")
     * @var File
     */
    private $imageFile;

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
    /**
     * @return File|null
     */
    public function getImageFilename(): ?File
    {
        return $this->imageFile;
    }
    /**
     * @return File|null $imageFile
     */
    public function setImageFilename(File $imgOffre = null): ?File
    {
        $this->imageFile = $imgOffre;
        return $this;
    }
    public function getImagePath()
    {
        return uploader::PROFILE_IMAGE.'/'.$this->getImageFilename();
    }

}
