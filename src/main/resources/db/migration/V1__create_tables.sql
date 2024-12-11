CREATE SEQUENCE IF NOT EXISTS users_id_seq;
CREATE TABLE users (
                        id bigserial PRIMARY KEY,
                        auth0_id VARCHAR(255) NOT  NULL UNIQUE,
                        username VARCHAR(50) NOT NULL UNIQUE,
                        display_name VARCHAR(50) NULL UNIQUE,
                        profile_picture TEXT NULL,
                        user_bio VARCHAR(255) NULL,
                        created_at TIMESTAMP DEFAULT NOW(),
                        updated_at TIMESTAMP DEFAULT NOW(),
                        enabled BOOLEAN NOT NULL
);


CREATE TABLE characters (
                        id bigserial PRIMARY KEY,
                        user_id BIGINT NOT NULL,
                        character_name VARCHAR(50) NULL DEFAULT 'Character Name',
                        character_description VARCHAR(255) NULL DEFAULT 'Character Bio/Description',
                        character_class VARCHAR(50) NULL,
                        character_subclass VARCHAR(50) NULL,
                        character_species VARCHAR(50) NULL,
                        character_subspecies VARCHAR(50) NULL,
                        character_origin VARCHAR(50) NULL,
                        character_level SMALLINT NOT NULL DEFAULT '1',
                        character_strength SMALLINT NOT NULL DEFAULT '8',
                        character_dexterity SMALLINT NOT NULL DEFAULT '8',
                        character_constitution SMALLINT NOT NULL DEFAULT '8',
                        character_intelligence SMALLINT NOT NULL DEFAULT '8',
                        character_wisdom SMALLINT NOT NULL DEFAULT '8',
                        character_charisma SMALLINT NOT NULL DEFAULT '8',
                        character_alignment VARCHAR(50) NULL,
                        character_current_hp SMALLINT NOT NULL DEFAULT '0',
                        character_max_hp SMALLINT NOT NULL DEFAULT '0',
                        character_armour_class SMALLINT NOT NULL DEFAULT '0',
                        character_initiative SMALLINT NOT NULL DEFAULT '0',
                        character_exp INTEGER NOT NULL DEFAULT '0',
                        character_coin SMALLINT NOT NULL DEFAULT '0',
                        character_proficiency_bonus SMALLINT NOT NULL DEFAULT '0',
                        created_at TIMESTAMP DEFAULT NOW(),
                        updated_at TIMESTAMP DEFAULT NOW(),
                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE character_spells (
                        id bigserial PRIMARY KEY,
                        character_id BIGINT NOT NULL,
                        spell_index VARCHAR(50) NOT NULL,
                        spell_level SMALLINT NOT NULL,
                        FOREIGN KEY (character_id) REFERENCES characters(id) ON DELETE CASCADE,
                        UNIQUE(character_id, spell_index)
);


CREATE TABLE character_equipment (
                        id bigserial PRIMARY KEY,
                        character_id BIGINT NOT NULL,
                        equipment_name VARCHAR (50) NOT NULL,
                        equipment_type VARCHAR(50) NULL,
                        quantity SMALLINT NOT NULL DEFAULT 1,
                        FOREIGN KEY (character_id) REFERENCES characters(id) ON DELETE CASCADE

);


CREATE TABLE character_proficiencies (
                        id bigserial PRIMARY KEY,
                        character_id BIGINT NOT NULL,
                        proficiency_index VARCHAR(50) NOT NULL,
                        FOREIGN KEY (character_id) REFERENCES characters(id) ON DELETE CASCADE,
                        UNIQUE(character_id, proficiency_index)
);


CREATE TABLE character_divine (
                        id bigserial PRIMARY KEY,
                        character_id BIGINT NOT NULL,
                        divine_order VARCHAR(50) NOT NULL,
                        FOREIGN KEY (character_id) REFERENCES characters(id) ON DELETE CASCADE
);


CREATE TABLE character_feats (
                        id bigserial PRIMARY KEY,
                        character_id BIGINT NOT NULL,
                        feat_index VARCHAR(50) NOT NULL,
                        FOREIGN KEY (character_id) REFERENCES characters(id) ON DELETE CASCADE
);
