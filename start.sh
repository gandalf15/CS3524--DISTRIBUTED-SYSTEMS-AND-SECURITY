#!/bin/bash

gnome-terminal \
	--window-with-profile=hold --hide-menubar --geometry=80x10 -e "rmiregistry 50010" \
	--window-with-profile=hold --hide-menubar --geometry=80x10 -e "java cs3524.solutions.mud.MUDServerMainline 50010 50011" \
	--window-with-profile=hold --hide-menubar --geometry=80x10 -e "java cs3524.solutions.mud.ClientMainline localhost 50010 50012" \
	--window-with-profile=hold --hide-menubar --geometry=80x10 -e "java cs3524.solutions.mud.ClientMainline localhost 50010 50013" \
	--window-with-profile=hold --hide-menubar --geometry=80x10 -e "java cs3524.solutions.mud.ClientMainline localhost 50010 50014" \
