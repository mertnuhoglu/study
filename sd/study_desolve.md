---
title: "Studying desolve"
date: 2020-02-27T10:48:23+03:00 
draft: false
description: ""
tags:
categories: system-dynamics
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

# Example Project

![Model](/Users/mertnuhoglu/Pictures/screenshots/20200227114658.png)

Check `~/codes/rr/SDMR/workshops/02 WPI 2017/models/01 SingleStock.R`

Model:

``` bash
# Setup simulation times and time step
START=2015; FINISH=2030; STEP=0.5

# Create time vector
simtime = seq(START, FINISH, by=STEP)

``` 

Constant values:

``` bash
# Create stock and auxs
stocks  = c(Customers = 10000)
auxs    = c(GrowthFraction = 0.08, DeclineFraction = 0.03)
``` 

Formulas:

``` bash
Recruits = Customers * GrowthFraction

Losses = Customers * DeclineFraction

dC_dt = Recruits - Losses
``` 

