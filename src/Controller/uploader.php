<?php

namespace App\Controller;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Gedmo\Sluggable\Util\Urlizer;
class uploader
{
    const PROFILE_IMAGE = 'profilepics';
public function uploadProfilePic(UploadedFile $uploadedFile):string
   {
       $destination = $this->uploadsPath.'/'.self::PROFILE_IMAGE;
        $originalFilename = pathinfo($uploadedFile->getClientOriginalName(), PATHINFO_FILENAME);
        $newFilename = Urlizer::urlize($originalFilename).'-'.uniqid().'.'.$uploadedFile->guessExtension();
        $uploadedFile->move($destination, $newFilename);
        return $newFilename;
    }
    private $uploadsPath;

    public function getPublicPath(string $path): string
    {
        return 'uploads/'.$path;
    }
    public function __construct(string $uploadsPath)
    {
        $this->uploadsPath = $uploadsPath;
    }
}