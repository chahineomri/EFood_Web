<?php

namespace App\Repository;

use App\Entity\RestaurantAttente;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method RestaurantAttente|null find($id, $lockMode = null, $lockVersion = null)
 * @method RestaurantAttente|null findOneBy(array $criteria, array $orderBy = null)
 * @method RestaurantAttente[]    findAll()
 * @method RestaurantAttente[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class RestaurantAttenteRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, RestaurantAttente::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(RestaurantAttente $entity, bool $flush = true): void
    {
        $this->_em->persist($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function remove(RestaurantAttente $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    // /**
    //  * @return RestaurantAttente[] Returns an array of RestaurantAttente objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('r.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?RestaurantAttente
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
