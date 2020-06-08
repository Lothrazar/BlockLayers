#!/bin/bash

echo "Useage: ./_.sh <destination>"
# for example, to make stairs_andesite block, use _.sh andesite stone_andesite

# I just happened to make granite first

dest=$1

original="clay"

modid="blocklayering"
folder="src/main/resources/data/${modid}/loot_tables/blocks"

# create the files

cp "${folder}"/layer_"${original}".json "${folder}"/layer_"${dest}".json


# replace blockids

sed -i -e "s/${original}/${dest}/g" "${folder}"/layer_"${dest}".json

echo "Files written"
