
-- -----------------------------------------------------
-- Schema cantiniere
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `cantiniere`.`autre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS taxe(
idtaxe SERIAL PRIMARY KEY ,
taxe DECIMAL(3,2) NOT NULL
);


-- -----------------------------------------------------
-- Table `cantiniere`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY kEY ,
    username VARCHAR(16) NOT NULL,
    userfname VARCHAR(20) NOT NULL,
    email  VARCHAR(255) NOT NULL ,
    password VARCHAR(300) NOT NULL,
    birthday  DATE NOT NULL,
    creation_date DATE NOT NULL ,
    credit DECIMAL(5,2)  DEFAULT 0 ,
    phone VARCHAR(16) NULL DEFAULT NULL,
    status   INT DEFAULT 1 ,       /* le 1  veut dire que utilisateur existe encore  0 son  compte est desactivé */
    UNIQUE (email),
    FOREIGN KEY (userimage) REFERENCES image (idimage)
    );


-- -----------------------------------------------------
-- Table `cantiniere`.`commande`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS commande (
     idcommande SERIAL PRIMARY KEY ,
     statut INT  NOT NULL,
     dateCreation DATE NOT NULL,
     heureCreation TIME ,
     user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
    );


-- -----------------------------------------------------
-- Table `cantiniere`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS image(
    idimage SERIAL PRIMARY KEY,
    imageName VARCHAR(400)
);


-- -----------------------------------------------------
-- Table `cantiniere`.`menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS menu (
     idMenu SERIAL PRIMARY KEY,
     status INT  NOT  NULL,
     label  VARCHAR(100) NOT NULL,
    description  TEXT NOT NULL,
    prixHt  DECIMAL(5,2) NOT NULL,
    image_idimage  INT NOT NULL,
    jourAssocier  VARCHAR(45) ,
    quantite INT NOT NULL ,
    FOREIGN KEY (image_idimage) REFERENCES image (idimage)
    );


-- -----------------------------------------------------
-- Table `cantiniere`.`commande_has_menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS commande_has_menu (
    commande_idcommande INT NOT NULL  ,
    menu_idMenu INT NOT NULL,
    PRIMARY KEY (commande_idcommande, menu_idMenu),
    FOREIGN KEY (commande_idcommande) REFERENCES commande (idcommande),
    FOREIGN KEY (menu_idMenu) REFERENCES menu (idMenu)
    );







-- -----------------------------------------------------
-- Table `cantiniere`.`plat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS plat(
    idplat SERIAL ,
    label VARCHAR(100) NOT NULL,
    description  TEXT NOT NULL ,
    prixHt  DECIMAL(5,2) NOT NULL,
    categorie  VARCHAR(45) NOT NULL,
    image_idimage  INT NOT NULL ,
    quantite  INT    DEFAULT 0 ,
    status INT  NOT NULL,
    PRIMARY KEY (idplat),
    FOREIGN KEY (image_idimage) REFERENCES image (idimage)
    );


-- -----------------------------------------------------
-- Table `cantiniere`.`quantite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS quantite (
    idquantite SERIAL ,
    quantite  INT NOT NULL ,
    menu_idMenu INT NULL,
    plat_idplat INT NULL,
    PRIMARY KEY (idquantite),
    FOREIGN KEY (menu_idMenu) REFERENCES menu (idMenu),
    FOREIGN KEY (plat_idplat) REFERENCES plat (idplat)
    );

-- -----------------------------------------------------
-- Table `cantiniere`.`commande_has_quantite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS commande_has_quantite (
    commande_idcommande INT NOT NULL,
    quantite_idquantite INT NOT NULL,
    PRIMARY KEY (commande_idcommande, quantite_idquantite),
    FOREIGN KEY (commande_idcommande)  REFERENCES commande (idcommande),
    FOREIGN KEY (quantite_idquantite)  REFERENCES quantite (idquantite)
);
-- -----------------------------------------------------
-- Table `cantiniere`.`menu_has_plat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS menu_has_plat (
    menu_idMenu INT NOT NULL,
    plat_idplat INT NOT NULL,
    PRIMARY KEY (menu_idMenu, plat_idplat),
    FOREIGN KEY (menu_idMenu) REFERENCES menu (idMenu)ON DELETE CASCADE,
    FOREIGN KEY (plat_idplat) REFERENCES  plat (idplat) ON DELETE RESTRICT
    );


-- -----------------------------------------------------
-- Table `cantiniere`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS role (
    idrole SERIAL  PRIMARY  KEY,
    libelle VARCHAR(45) NOT NULL,
    description TEXT
    );


-- -----------------------------------------------------
-- Table `cantiniere`.`user_has_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS  user_has_role (
    user_id INT NOT NULL  ,
    role_idrole INT NOT NULL,
    PRIMARY KEY (user_id, role_idrole),
    FOREIGN KEY (role_idrole) REFERENCES role (idrole)
    )


-- -----------------------------------------------------
-- Table `cantiniere`.`plat_has_commande`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS plat_has_commande (
    plat_idplat INT NOT NULL,
    commande_idcommande INT NOT NULL,
    PRIMARY KEY (plat_idplat, commande_idcommande),
    FOREIGN KEY (plat_idplat) REFERENCES plat (idplat) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (commande_idcommande) REFERENCES commande (idcommande) ON DELETE NO ACTION ON UPDATE NO ACTION
    );


-- table of  meal Confirmation


CREATE TABLE confirmationToken (
   tokenid SERIAL PRIMARY KEY,
   confirmationToken VARCHAR(255),
   createdDate TIMESTAMP,
   user_id INTEGER NOT NULL REFERENCES users(id)
);