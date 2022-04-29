<?php

namespace App\Entity;

use App\Controller\uploader;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Validator\Constraints as Assert;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;


/**
 * User
 *
 * @ORM\Table(name="user", uniqueConstraints={
 * @ORM\UniqueConstraint(name="EmailUser", columns={"EmailUser"})})
 * @ORM\Entity(repositoryClass="App\Repository\UserRepository")
 * @ParamConverter("user", options={"id" = "iduser"})
 */
class User implements UserInterface, UserPasswordEncoderInterface
{

    /**
     * @var int
     *
     * @ORM\Column(name="IdUser", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $iduser;

    /**
     * @var string
     *
     * @ORM\Column(name="NameUser", type="string", nullable=false)
     */
    private $nameuser;

    /**
     * @var string
     *
     * @ORM\Column(name="LastNameUser", type="string", nullable=false)
     */
    private $lastnameuser;

    /**
     * @var string
     *
     * @ORM\Column(name="EmailUser", type="string", length=60, nullable=false, unique=true)
     * @Assert\Email(
     *  message = "The email '{{ value }}' is not a valid email.",
     *  checkMX = true
     * )
     */
    private $emailuser;

    /**
     * @var string|null
     *
     * @ORM\Column(name="ProfilePicUser", type="string", length=200, nullable=true)
     */
    private $profilepicuser;

    /**
     * @ORM\Column(name="PasswordUser",type="string",nullable=false)
     */
    private $passworduser;

    /**
     *
     * @ORM\Column(name="UserRole", type="json")
     */
    private $userrole=[];

    /**
     * @var bool
     *
     * @ORM\Column(name="UserStatus", type="boolean", nullable=false, options={"default"="1"})
     */
    private $userstatus = true;

    /**
     * @ORM\Column(type="datetime")
     */
    private $agreedTermsAt;

    private $plainPassword;
    private $roles;

    /**
     * @return int
     */
    public function getIduser(): int
    {
        return $this->iduser;
    }

    /**
     * @param int $iduser
     */
    public function setIduser(int $iduser): void
    {
        $this->iduser = $iduser;
    }

    /**
     * @return string
     */
    public function getNameuser(): ?string
    {
        return $this->nameuser;
    }

    /**
     * @param string $nameuser
     */
    public function setNameuser(string $nameuser): void
    {
        $this->nameuser = $nameuser;
    }

    /**
     * @return string
     */
    public function getLastnameuser(): ?string
    {
        return $this->lastnameuser;
    }

    /**
     * @param string $lastnameuser
     */
    public function setLastnameuser(string $lastnameuser): void
    {
        $this->lastnameuser = $lastnameuser;
    }

    /**
     * @return string
     */
    public function getEmailuser(): ?string
    {
        return $this->emailuser;
    }

    /**
     * @param string $emailuser
     */
    public function setEmailuser(string $emailuser): void
    {
        $this->emailuser = $emailuser;
    }

    /**
     * @return string|null
     */
    public function getProfilepicuser(): ?string
    {
        return $this->profilepicuser;
    }

    /**
     * @param string|null $profilepicuser
     * @return User
     */
    public function setProfilepicuser(?string $profilepicuser): self
    {
        $this->profilepicuser = $profilepicuser;
        return $this;
    }

    /**
     * @return array
     */
    public function getUserrole(): array
    {
        $userrole= $this->userrole;
        $userrole[] = 'ROLE_USER';
        return array_unique($userrole);
    }

    /**
     * @param array $userrole
     * @return User
     */
    public function setUserrole(array $userrole): self
    {
        $this->userrole = $userrole;
        return $this;
    }

    public function getRoles(): array
    {
        $userrole= $this->userrole;
        $userrole[] = 'ROLE_USER';
        return array_unique($userrole);
    }
    /**
     * @param array $userrole
     * @return User
     */
    public function setRoles(array $userrole): self
    {
        $this->userrole = $userrole;
        return $this;
    }

    /**
     * @return bool
     */
    public function isUserstatus(): bool
    {
        return $this->userstatus;
    }

    /**
     * @param bool $userstatus
     */
    public function setUserstatus(bool $userstatus): void
    {
        $this->userstatus = $userstatus;
    }

    public function getAgreedTermsAt(): ?\DateTimeInterface
    {
        return $this->agreedTermsAt;
    }

    public function agreeTerms()
    {
        $this->agreedTermsAt = new \DateTime();

    }



public function getPassword(): ?string
{
    return $this->passworduser;
}
    public function setPassword(string $passworduser) :self
    {
        $this->passworduser = $passworduser;
        return $this;
    }

    public function getSalt()
    {
        // TODO: Implement getSalt() method.
    }

    public function getUsername()
    {
        // TODO: Implement getUsername() method.
    }

    public function eraseCredentials()
    {
        $this->plainPassword = null;
    }

    public function encodePassword(UserInterface $user, $plainPassword)
    {
        // TODO: Implement encodePassword() method.
    }

    public function isPasswordValid(UserInterface $user, $raw)
    {
        // TODO: Implement isPasswordValid() method.
    }

    public function __call(string $name, array $arguments)
    {
        // TODO: Implement @method bool needsRehash(UserInterface $user)
    }

    public function getPlainPassword(): ?string
    {
        return $this->plainPassword;
    }

    public function setPlainPassword(string $plainPassword): self
    {
        $this->plainPassword = $plainPassword;

        return $this;
    }
    public function getImageFilename(): ?string
    {
        return $this->profilepicuser;
    }
    public function setImageFilename(?string $imageFilename): self
    {
        $this->profilepicuser = $imageFilename;
        return $this;
    }
    public function getImagePath()
    {
        return uploader::PROFILE_IMAGE.'/'.$this->getImageFilename();
    }

    public function getPassworduser(): ?string
    {
        return $this->passworduser;
    }

    public function setPassworduser(string $passworduser): self
    {
        $this->passworduser = $passworduser;

        return $this;
    }

    public function getUserstatus(): ?bool
    {
        return $this->userstatus;
    }

    public function setAgreedTermsAt(\DateTimeInterface $agreedTermsAt): self
    {
        $this->agreedTermsAt = $agreedTermsAt;

        return $this;
    }

}
