---
title: "Book: Applied Statistics and Probability for Engineers by Douglas C. Montgomery, George C. Runger"
date: 2020-09-02T22:03:16+03:00 
draft: false
description: ""
tags:
categories: statistics
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

## ch13: Design and Analysis of Single-Factor Experiments: The Analysis of Variance

### Introduction 

Ex: statistical hypothesis method for two samples

- single factor
- levels of the factor
- t-test: decide if the two means differ

Ex: single-factor experiment with more than two levels of the factor

investigate 5 curing methods

use analysis of variance (ANOVA): to compare means

randomization of experimental runs

### 13.1: Designing Engineering Experiments

Designed experiments are employed sequentially:

1. First experiment is a screening experiment.

It determines most important variables.

2. Later experiments: determine which adjustments for variables are needed

3. Objective of experiment: optimization

Determine the levels of variables that result in best process performance.

All experiments are designed experiments. Some are poorly designed.

### 13.2: Completely Randomized Single-Factor Experiment

Ex: Tensile Strength

Treatment: levels of the factor

Replicates: observations. Ex: Each treatment has six observations.

Role of randomization:

By randomizing the order of the 24 runs, the effect of any nuisance variable that may influence the observed tensile strength is balanced out approx.

Graphical interpretation of the data:

Box plots: 

- variability of the observations within a treatment (factor level)
- variability between treatments

#### 13.2.2: Analysis of Variance

Response for each n treatments is a random variable.

$y_{ij}$ : jth observation taken under treatment i

linear statistical model:

$Y_{ij} = \mu + \tau_i + \epsilon_{ij}$ 

$\mu$ : overall mean

$\tau_i$: ith treatment effect

$\epsilon$: random error component

Assume: $\epsilon$ are iid normal with mean zero and variance $\sigma^2$

Completely randomized design (CRD): 

- observations are taken in random order
- environment: experimental unit: as uniform as possible

Fixed-effects model:

- test hypotheses about treatment means
- estimate treatment effects

ANOVA for the fixed-effects model

ANOVA can be used:

- regression analysis
- testing for equality of treatment effects

Test:

$H_0: \tau_1 = \tau_2 = ... = \tau_n = 0$

Equivalent to: all N observations are taken from a normal dist with mean $\mu$ and variance $\sigma^2$.

$SS_{treatments}$: treatment sum of squares

$SS_{E}$: error sum of squares

$MS_{treatments}$

ANOVA F-Test:

$F_0 = ...$

#### 13.2.3: Multiple Comparisons Following the ANOVA

When the null hypothesis is rejected in ANOVA, we know some factor-level means are diffrenet. But which?

Methods to find this: multiple comparisons methods.

Simple one: Fisher's least significant difference method

Ex: LSD = 3.07. Thus any pair of treatment means that differs by more than 3.07 implies: that pair of means are different.

#### 13.2.4: Residual Analysis and Model Checking





