ALTER TABLE characters
ADD COLUMN background_name VARCHAR(255) NOT NULL,
ADD COLUMN background_skill_proficiency_1 VARCHAR(255) NOT NULL,
ADD COLUMN background_skill_proficiency_2 VARCHAR(255) NOT NULL,
ADD COLUMN background_ability_score_1 VARCHAR(255) NOT NULL,
ADD COLUMN background_ability_score_2 VARCHAR(255) NOT NULL,
ADD COLUMN background_ability_score_3 VARCHAR(255) NOT NULL,
ADD COLUMN background_tool_proficiency VARCHAR(255) NOT NULL,
ADD COLUMN background_feat VARCHAR(255) NOT NULL;