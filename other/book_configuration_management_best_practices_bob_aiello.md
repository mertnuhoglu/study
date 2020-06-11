---
title: "Studying R"
date: 2018-04-14T16:06:01+03:00 
draft: false
description: ""
tags:
categories: r
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

# Book: Configuration Management Best Practices Practical Methods that Work in the Real World by Robert Aiello, Leslie Sachs id=g_10952

## Terminology

### 14.3.1 Configuration Item id=g_11198

Possible choices:

- code files
- build and package files
- configuration files
- word docs
- xml files
- hardware components

Anything required in a release is a configuration item.

### 14.3.2 Configuration Identification id=g_11199

Select and identify correct CIs. This includes:

- immutable version ID for each CI
- a proceuder to retrieve this version ID
- creating a unique naming convention for:
	- baselines
	- packages

This enables you to identify exact version of a CI regardless of whether it is SCM (like git).

### 14.3.3 Configuration Control id=g_11200

Configuration control is used to control all changes to CIs, including:

- what the change was
- who made the change
- authorization for the change

CC requires:

- unauthorized changes do not occur
- revert change back
- control exact versions of CIs that will be part of a particular baseline

a priori change control: permission is requested to make change before the change actually occurs.

rightsizing change control process: loose change control in the beginning and then more strictly when application has been released

### 14.3.4 Interface Control

components have interface dependencies such as:

- simple: pointing to correct database (ex: qa, prod)
- complex: that include behavior across firewalls, proxies

### 14.3.5 Configuration Status Accounting

configuration status accounting (csa): tracing a CI through its complete lifecycle

### 14.3.6 Configuration Audit id=g_11201

verifying physical and functional characteristics of any CI

misconception: it is sufficient to track CIs while they are in SCM. 

in fact, they should be identifiable even when the code is in production or QA.

opt01: all CIs have immutable version IDs inside. a script extracts them and generates list of CIs and their version. it is called `release map`

configuration audit can be done by generating release map and comparing it to the copy shipped when the release was first deployed.

@mine: yani prod'daki ci, bizim ilk teslim ettiğimiz ci mı? yoksa bir şey değişmiş mi?

### 14.3.7 Subcontractor/Vendor Control

### 14.3.8 Conformance Versus Noncompliance

Practices required for compliance are usually indicated by the word shall, whereas suggested practices are indicated with the word should.

### Buses and Trains Should Run on Time

Deming’s point was that there is no possible excuse for accepting poor quality as a norm. Similarly, release management should be automated, reliable, traceable, and verifiable.

Sadly, many organizations just assume that release man- agement cannot be controlled, just as trains and buses will be habitually late.

### 14.6.1.3.4 Putting Together a Configuration and Change Management Framework

change control process:

1. submit change requests (CRs)

2. CCB reviews them 

review process: based on assessing and mitigating risk

thus focus on important issues

essential part of review: automated procedures to promote releases and fallback to a previous release.

deployment should be automated.

alternative flow for emergencies: emergency change control process

1. submit change requests (CRs)

2. a very senior manager approves them 

### 1.3.1 Creating Baselines and Time Machines id=g_11202

checking in source code to VCS is not sufficient as a CM process.

the whole point of SCM is to provide a time machine to bring you back to a speific point in time when it is a stable release.

baseline: identifying exact versions of code for a specific release

many CM tools call this as `tagging` or `labeling` or `snapshotting`.

baselines have to be immutable.

in addition to tag for creating baselines, people use an additional tag `PRODUCTION` to indicate current release in production.

this tag is said to "float" with current baseline. 

some tools implement tagging as `metadata`. 

metadata can include:

- check-in comments
- references to related defects or requests
- links documenting code merges

all changes to code should be tracked to a CR

@mine: cm aslında bağımlılıkların yönetimini sağlıyor. bağımlılıkların sorunu şu ki, bunlar aslında side-effect; çünkü immutable değiller. rich hickey'in deyişiyle place bunlar, content değil. cm bunların place değil, immutable content olmalarını sağlıyor.



