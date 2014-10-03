
# The Legacy Code Change Algorithm (M)

1. identify change points
2. find test points
3. break dependencies
4. write tests
5. make changes and refactor

----

# General Legacy Management Strategy (M)

(from http://www.objectmentor.com/resources/articles/WorkingEffectivelyWithLegacyCode.pdf)

* identify change points
* find an inflection point
* cover the inflection point
    * break external dependencies
    * break internal dependencies
    * write tests
* make changes
* refactor the covered code

_An inflection point is a narrow interface to a set of classes. If anyone changes any of 
the classes behind an inflection point, the change is either detectable at the inflection 
point, or inconsequential in the application._

----

# The Seam Model (M)

_A **seam** is a place where you can alter behaviour in your program without editing in that place._


_Every seam has an **enabling point**, a place where you can make the decision to use one behaviour or another._

## Seam types

* _Preprocessing Seam_, e.g. macro preprocessor in C and C++
* _Link Seam_, e.g. replace classes by patching, i.e. putting them before the _real_ ones in the classpath
    * seam: the call to the class that is patched, e.g. new PersistentObject()
    * enabling point: the classpath
* _Object Seam_, e.g. replace an object by a new subclass, override an interesting function
    * enabling points: constructors, parameters
    
Sometimes, we have to change some code to make something a seam. E.g. static -> non static, can then be overridden.

----

# Effect Analysis

----

# How Do I Know That I'm Not Breaking Anything? (M)

* lean on the compiler
* monitoring
* logging

----

# I Need to make a change, but I don't know what tests to write

## Characterization tests

1. Write a test for a piece of code
2. Write an assertion that fails
3. Let the failure tell what the behaviour is
4. Change the assertion to expect the behaviour
5. Repeat

## Characterizing classes

1. Look for tangled piece of logic
2. Make list of things that can go wrong, write test to trigger them
3. Consider extreme values for test input
4. Write tests to verify class invariants

## Targeted testing

After writing characterization test, look at the code you want to change and make sure that the tests really cover that part of code

----

# I Don't Understand the Code Well Enough to Change it (M)

* notes / sketching
* listing markup
* scratch refactoring
* delete unused code

----

# Dependency-breaking techniques

----

# Example: legacy singletons (M)

----

# Example: service in domain model class (M)

I.e., for JEE: CDI in JPA

----

# Misc

* low risk changes

----

# Prioritering av endringer i legacy kode

which part of the code

* contains most problems?
* most responsibility?
* runs slowest? 
* contains most bugs?
* causes most maintenance?

what do we mean by problem, responsibility, etc, and how do we determine that?

----

# References

[Working Effectively With Legacy Code -pdf ](http://www.objectmentor.com/resources/articles/WorkingEffectivelyWithLegacyCode.pdf)

[Working Effectively With Legacy Code - book](https://www.adlibris.com/no/product.aspx?isbn=0131177052)
