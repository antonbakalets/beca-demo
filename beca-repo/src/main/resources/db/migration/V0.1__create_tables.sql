-- -----------------------------------------------------
-- Table User_Login
-- -----------------------------------------------------
CREATE TABLE user_login (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    username            VARCHAR(100) NOT NULL,
    password_hash       VARCHAR(100) NOT NULL,
    first_name          VARCHAR(100) NOT NULL,
    last_name           VARCHAR(100) NOT NULL
);

-- -----------------------------------------------------
-- Table Account
-- -----------------------------------------------------
CREATE TABLE account (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    total           DECIMAL(15, 5) NOT NULL,
    owner           INT NOT NULL,
    CONSTRAINT fk_acco_user
        FOREIGN KEY (owner) REFERENCES user_login (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE INDEX in_acco_user ON account(owner ASC);

-- -----------------------------------------------------
-- Table Tx_action
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tx_action (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    tx_date         DATE NOT NULL,
    account         INT NULL,
    amount          DECIMAL(15, 5) NULL,
    comment         VARCHAR(250) NULL,
    CONSTRAINT fk_tx_account
        FOREIGN KEY (account) REFERENCES account (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE INDEX in_tx_account ON tx_action(account ASC);