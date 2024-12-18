-- Species Images --
UPDATE species_extra_details
SET species_image = '/images/species_images/dragonborn.png'
WHERE id = 1;

UPDATE species_extra_details
SET species_image = '/images/species_images/dwarf.png'
WHERE id = 2;

UPDATE species_extra_details
SET species_image = '/images/species_images/elf.png'
WHERE id = 3;

UPDATE species_extra_details
SET species_image = '/images/species_images/gnome.png'
WHERE id = 4;

UPDATE species_extra_details
SET species_image = '/images/species_images/half-elf.png'
WHERE id = 5;

UPDATE species_extra_details
SET species_image = '/images/species_images/half-orc.png'
WHERE id = 6;

UPDATE species_extra_details
SET species_image = '/images/species_images/halfling.png'
WHERE id = 7;

UPDATE species_extra_details
SET species_image = '/images/species_images/human.png'
WHERE id = 8;

UPDATE species_extra_details
SET species_image = '/images/species_images/tiefling.png'
WHERE id = 9;

-- Background Images --
ALTER TABLE backgrounds
    ADD background_image text;

UPDATE backgrounds
SET background_image = '/images/background_images/acolyte.jpg'
WHERE id = 1;

UPDATE backgrounds
SET background_image = '/images/background_images/artisan.jpg'
WHERE id = 2;

UPDATE backgrounds
SET background_image = '/images/background_images/charlatan.jpg'
WHERE id = 3;

UPDATE backgrounds
SET background_image = '/images/background_images/criminal.jpg'
WHERE id = 4;

UPDATE backgrounds
SET background_image = '/images/background_images/entertainer.jpg'
WHERE id = 5;

UPDATE backgrounds
SET background_image = '/images/background_images/farmer.jpg'
WHERE id = 6;

UPDATE backgrounds
SET background_image = '/images/background_images/guard.jpg'
WHERE id = 7;

UPDATE backgrounds
SET background_image = '/images/background_images/guide.jpg'
WHERE id = 8;

UPDATE backgrounds
SET background_image = '/images/background_images/hermit.jpg'
WHERE id = 9;

UPDATE backgrounds
SET background_image = '/images/background_images/merchant.jpg'
WHERE id = 10;

UPDATE backgrounds
SET background_image = '/images/background_images/noble.jpg'
WHERE id = 11;

UPDATE backgrounds
SET background_image = '/images/background_images/sage.jpg'
WHERE id = 12;

UPDATE backgrounds
SET background_image = '/images/background_images/sailor.jpg'
WHERE id = 13;

UPDATE backgrounds
SET background_image = '/images/background_images/scribe.jpg'
WHERE id = 14;

UPDATE backgrounds
SET background_image = '/images/background_images/soldier.jpg'
WHERE id = 15;

UPDATE backgrounds
SET background_image = '/images/background_images/wayfarer.jpg'
WHERE id = 16;