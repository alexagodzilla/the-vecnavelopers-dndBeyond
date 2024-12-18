-- Class Images --

ALTER TABLE class_extra_details
    ADD class_image text;

UPDATE class_extra_details
SET class_image = '/images/class_images/barbarian.png'
WHERE id = 1;

UPDATE class_extra_details
SET class_image = '/images/class_images/bard.png'
WHERE id = 2;

UPDATE class_extra_details
SET class_image = '/images/class_images/cleric.png'
WHERE id = 3;

UPDATE class_extra_details
SET class_image = '/images/class_images/druid.png'
WHERE id = 4;

UPDATE class_extra_details
SET class_image = '/images/class_images/fighter.png'
WHERE id = 5;

UPDATE class_extra_details
SET class_image = '/images/class_images/monk.png'
WHERE id = 6;

UPDATE class_extra_details
SET class_image = '/images/class_images/paladin.png'
WHERE id = 7;

UPDATE class_extra_details
SET class_image = '/images/class_images/ranger.png'
WHERE id = 8;

UPDATE class_extra_details
SET class_image = '/images/class_images/rogue.png'
WHERE id = 9;

UPDATE class_extra_details
SET class_image = '/images/class_images/sorcerer.png'
WHERE id = 10;

UPDATE class_extra_details
SET class_image = '/images/class_images/warlock.png'
WHERE id = 11;

UPDATE class_extra_details
SET class_image = '/images/class_images/wizard.png'
WHERE id = 12;


