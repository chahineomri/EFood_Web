<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220428192018 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE administrateur (ID INT AUTO_INCREMENT NOT NULL, nom VARCHAR(256) NOT NULL, prénom VARCHAR(256) NOT NULL, pwd VARCHAR(256) NOT NULL, PRIMARY KEY(ID)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE client (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(256) NOT NULL, prénom VARCHAR(256) NOT NULL, pwd VARCHAR(256) NOT NULL, mail VARCHAR(50) NOT NULL, type_user INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE commande (id_commande INT AUTO_INCREMENT NOT NULL, id_client INT NOT NULL, nom_client VARCHAR(256) NOT NULL, plat_cmd VARCHAR(256) NOT NULL, lieu_cmd VARCHAR(256) NOT NULL, PRIMARY KEY(id_commande)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE commentaire (id INT AUTO_INCREMENT NOT NULL, id_restaurant_id INT DEFAULT NULL, id_user_id INT DEFAULT NULL, contenu VARCHAR(255) NOT NULL, date_creation DATETIME NOT NULL, INDEX IDX_67F068BCFCFA10B (id_restaurant_id), INDEX IDX_67F068BC79F37AE5 (id_user_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE evenements (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(100) NOT NULL, jour INT NOT NULL, id_resto INT NOT NULL, mois INT NOT NULL, annee INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE gerant (ID INT AUTO_INCREMENT NOT NULL, nom VARCHAR(256) NOT NULL, prénom VARCHAR(256) NOT NULL, pwd VARCHAR(256) NOT NULL, PRIMARY KEY(ID)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE menu (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(256) NOT NULL, quantite INT NOT NULL, prix INT NOT NULL, imgSrc LONGBLOB NOT NULL, saison VARCHAR(256) NOT NULL, date_mise_en_rayon DATE NOT NULL, date_peremption DATE NOT NULL, id_restaurant INT NOT NULL, like_menu INT DEFAULT NULL, dislike_menu INT DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE note (id INT AUTO_INCREMENT NOT NULL, id_client_id INT DEFAULT NULL, id_restaurant_id INT DEFAULT NULL, menu_id INT DEFAULT NULL, note INT NOT NULL, INDEX IDX_CFBDFA1499DED506 (id_client_id), INDEX IDX_CFBDFA14FCFA10B (id_restaurant_id), INDEX IDX_CFBDFA14CCD7E912 (menu_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE panier (id_panier INT AUTO_INCREMENT NOT NULL, id_commande INT NOT NULL, prix INT NOT NULL, qte INT NOT NULL, total INT NOT NULL, PRIMARY KEY(id_panier)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reservations (id INT AUTO_INCREMENT NOT NULL, id_resto INT NOT NULL, jour INT NOT NULL, mois INT NOT NULL, annee INT NOT NULL, heureR INT NOT NULL, Descrp VARCHAR(256) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE restaurant (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(256) NOT NULL, position VARCHAR(256) NOT NULL, date_entrer DATE NOT NULL, image LONGBLOB NOT NULL, gerant_restaurant VARCHAR(256) NOT NULL, like_restaurant INT DEFAULT NULL, dislike_restaurant INT DEFAULT NULL, id_client INT DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE restaurant_attente (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(256) NOT NULL, position VARCHAR(256) NOT NULL, gerant_restaurant VARCHAR(256) NOT NULL, email VARCHAR(256) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT FK_67F068BCFCFA10B FOREIGN KEY (id_restaurant_id) REFERENCES restaurant (id)');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT FK_67F068BC79F37AE5 FOREIGN KEY (id_user_id) REFERENCES client (id)');
        $this->addSql('ALTER TABLE note ADD CONSTRAINT FK_CFBDFA1499DED506 FOREIGN KEY (id_client_id) REFERENCES client (id)');
        $this->addSql('ALTER TABLE note ADD CONSTRAINT FK_CFBDFA14FCFA10B FOREIGN KEY (id_restaurant_id) REFERENCES restaurant (id)');
        $this->addSql('ALTER TABLE note ADD CONSTRAINT FK_CFBDFA14CCD7E912 FOREIGN KEY (menu_id) REFERENCES menu (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY FK_67F068BC79F37AE5');
        $this->addSql('ALTER TABLE note DROP FOREIGN KEY FK_CFBDFA1499DED506');
        $this->addSql('ALTER TABLE note DROP FOREIGN KEY FK_CFBDFA14CCD7E912');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY FK_67F068BCFCFA10B');
        $this->addSql('ALTER TABLE note DROP FOREIGN KEY FK_CFBDFA14FCFA10B');
        $this->addSql('DROP TABLE administrateur');
        $this->addSql('DROP TABLE client');
        $this->addSql('DROP TABLE commande');
        $this->addSql('DROP TABLE commentaire');
        $this->addSql('DROP TABLE evenements');
        $this->addSql('DROP TABLE gerant');
        $this->addSql('DROP TABLE menu');
        $this->addSql('DROP TABLE note');
        $this->addSql('DROP TABLE panier');
        $this->addSql('DROP TABLE reservations');
        $this->addSql('DROP TABLE restaurant');
        $this->addSql('DROP TABLE restaurant_attente');
    }
}
