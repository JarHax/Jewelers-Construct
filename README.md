# Jewelers Construct

This mod allows players to create baubles out of the various resources in their game. Modifiers can be applied to the baubles in this mod to give stat boosts and other abilities. 

## ModJam
This mod was made in four days as part of ModJam 5. Voting for ModJam is now open, you can leave your vote [here](https://docs.google.com/forms/d/e/1FAIpQLSeYHcTSuso40juM8l2H1CUsrATxiMxIfQwZEMfHPVGo50yLRg/viewform). Due to an issue when we submitted, our mod is listed as `ModJam5-1.12.2-1.8.0.jar` in the team category, so if you want to vote for us, that's how we show up on the list. Sorry for the confusion. 

If you have an issue with this mod crashing, please download the latest version on this document. We will be fixing issues as they are reported. 

## Future updates
The textures, models, content, and general balancing in this mod is largely a proof of concept that was made in 4 days for ModJam 5. We are planning to continue development of this mod but further polish is needed before we will be officially releasing it on Curse. A branch for the polished version has been created and can be found [here](https://github.com/JarHax/ModJam-5/tree/polish). 

## Downloads
[1.8.0](http://i.blamejared.com/ModJam5-1.12.2-1.8.0.jar)
## Getting Started (Quick Start)

**Building a bauble**
1. Craft a Part Shaper using stone and iron bars.
2. Put some furnace fuel in the bottom slot, and a valid material in the top. Materials include ingots and plates of a material.
3. Select what type of part you want to make by clicking on the left squares. Then click the shape button. A ring requires a band, a binding and a gem.
4. Craft a Jewelers Forge using wooden slabs, wooden planks and a log of wood. 
5. Open the Jewelers Forge and select which bauble you want to make. 
6. Place the parts in the designated slots of the Jewelers Forge.
7. Take out your bauble. 

**Applying Modifiers**
1. Get a bauble and a modifier item. 
2. Put the bauble in the top slot and the modifier in one of the lower four slots.
3. If the modifier is valid for the bauble you can take it out of the output slot.

## Mechanics

### Durability
Baubles crafted using this mod have a durability value. This is used to determine how much you can use the bauble, the more you use it the more it is worn out. The amount of durability a bauble has is determined by the materials and modifiers used. Currently each modifier uses one durability every 30 seconds it is worn. This will be changed in the polish update.

### Modifier Count
Baubles crafted with this mod have a modifier count value. This is used to determine how many modifiers can be applied to the bauble. Every level/tier of a modifier counts towards this total. Different materials contribute different amounts to this value. 

## Tips
- Using multiple modifier items of the same type can increase the tier of the modifier. Look at the modifier list to find out which modifiers have more than one tier. 
- You can find creative modifiers in the creative tab. These can be used to apply specific tiers of modifiers.
- Adding other mods can increase the selection of parts that players can use. 

## Materials

### Vanilla
| Name        | Durability | Modifiers |
|-------------|------------|-----------|
| Wooden      | 60         | 1         |
| Flint       | 70         | 1         |
| Bone        | 80         | 1         |
| Stone       | 120        | 1         |
| Iron        | 256        | 1         |
| Gold        | 60         | 3         |

### Vanilla Gems
| Name        | Durability | Modifiers |
|-------------|------------|-----------|
| Prismarine  | 30         | 0         |
| Emerald     | 45         | 0         |
| Diamond     | 80         | 0         |

### Common Modded Metals
| Name        | Durability | Modifiers |
|-------------|------------|-----------|
| Copper      | 260        | 1         |
| Bronze      | 350        | 1         |
| Lead        | 355        | 1         |
| Silver      | 300        | 2         |
| Electrum    | 285        | 3         |
| Steel       | 500        | 1         |

### Tinkers Construct Addon
| Name        | Durability | Modifiers |
|-------------|------------|-----------|
| Ardite      | 260        | 1         |
| Cobalt      | 350        | 1         |
| Manyullyn   | 355        | 1         |
| Pigiron     | 300        | 2         |
| Knightslime | 285        | 3         |

## Modifier List

| Name                                                | Max Lv. | Crafted With    | Description |
|-----------------------------------------------------|---------|-----------------|-------------|
| Vigor                                               | 5       | Iron Sword      | Every tier gives the player an additiona 0.75 hearts. |
| Vitality                                            | 5       | Golden Apple    | Raises the max health of the user by one heart per tier.            |
| Fortitude                                           | 3       | Iron Chestplate | Provides the player with two armor points per tier.             |
| Sturdy                                              | 3       | Anvil           | Gives the user 5% more knockback resistance per tier.            |
| Luck                                                | 3       | Rabbit's Foot   | Improves the quality and amount of loot by one per tier.            |
| Grasping                                            | 3       | Fishing Rod     | Increases the block break/place distance by 1.25 blocks per tier.            |
| Swift                                               | 3       | Sugar           | Increases attack speed by 0.33 attacks per second.            |
| Traveler                                            | 5       | Minecart        | Increases movement speed by 10% per tier.            |
| Step Up                                             | 1       | Stone Slab      | Allows the user to auto climb blocks.            |
| Lightweight                                         | 1       | Feather         | Allows the player to sneak while falling to slow down how fast they fall.            |
| Food                                                | 1       | Steak           | Replenishes the food of the player.             |
| Saturation                                          | 1       | Golden Carrot   | Replenishes the saturation of the player.            |
| Magnetism                                           | 1       | Sticky Piston   | Allows the player to pick up items that are further away.            |

## FAQ

**Do modifiers stack?**    
Modifiers of the same type do not stack. If the player has two modifiers of the same type, only the highest tier will be applied. For example, if the player has a Vitality 3 ring, and a Vitality 2 belt, they will get three hearts and not five. If the modifiers are of the same tier, only the first one detected will be applied. Players **can** use multiple unique modifiers at the same time, only the same modifiers will not stack.

**Are the baubles meant to be white/gray?**    
The baubles are gray in the modjam version because we didn't have time to implement the colored model system for them. In the polished version that will be released on Curse, this each of the parts will show up as a different color in the bauble, similar to how parts in tinkers construct change the colour of the tool. 
