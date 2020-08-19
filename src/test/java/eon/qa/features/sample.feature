#-----------------------------------------------------------------------------------------
#Sample Feature Created For Knowledge Purpose
#@Created By: Praveen Kalliyath
#-----------------------------------------------------------------------------------------
Feature: Sample karate test scripts

  #-----------------------------------------------------------------------------------------
  #Backround Scenarios for pre initialization
  #-----------------------------------------------------------------------------------------
  Background: 
    #url value fetched from karate-config.js
    * url baseuri
    * def dataClass = Java.type('eon.qa.testutils.Data')
    * def commonutilClass = Java.type('eon.qa.testutils.CommonUtil')
    * def fakerClass = Java.type('eon.qa.testutils.DataFaker')
    * def propertiesClass = Java.type('eon.qa.testutils.PropertiesUtil')
    * def excelClass = Java.type('eon.qa.testutils.ExcelUtil')

  #-----------------------------------------------------------------------------------------
  #Simple GET Request
  #-----------------------------------------------------------------------------------------
  Scenario: Simple GET Request
    Given path 'users'
    When method get
    Then status 200
    * def first = response[0]
    Given path 'users', first.id
    When method get
    Then status 200

  #-----------------------------------------------------------------------------------------
  #Write Response To Properties File
  #-----------------------------------------------------------------------------------------
  @propertiesfiles
  Scenario: Simple GET Request
    Given path 'users'
    When method get
    Then status 200
    * def first = response[0]
    Given path 'users', first.id
    When method get
    Then status 200
    Then propertiesClass.writeToConfigFile('user',first.id)

  #-----------------------------------------------------------------------------------------
  #Simple PUt Request
  #-----------------------------------------------------------------------------------------
  Scenario: Simple PUT request scenario
    Given def dataClass = Java.type('eon.qa.testutils.Data')
    Given def path = dataClass.JSON_FOLDER
    Given def file = path + 'getUserData.json'
    Given print 'Json File is: ', file
    Given def json = read(file)
    Then print 'json - ', json
    Given path '/users'
    And request json
    When method post
    Then status 201
    Then print 'Response:', response
    * def id = response.id
    * print 'Created Id is: ', id
    Given path id

  #-----------------------------------------------------------------------------------------
  #Example SET, REMOVE AND REPLACE
  #-----------------------------------------------------------------------------------------
  @editJson
  Scenario: Scenario to demonstrate functions like  set remove and replace
    * def json =
      """
      {
      name:'Tim',
      age:27
      }
      """
    Given print 'Original Json is ', json
    * set json.gender = 'male'
    * set json.country = 'India'
    Given print 'Json After Set is ', json
    * remove json.gender
    Given print 'Json After Remove is ', json
    Given def sampletext = 'Hi, My Name is <name>'
    * replace sampletext.name = 'Praveen'
    Given print 'Sample Text after replacing is ', sampletext

  #-----------------------------------------------------------------------------------------
  #Example Calling Methods From Java Class
  #-----------------------------------------------------------------------------------------
  @javasample
  Scenario: Scenario To Access Java Class Methods N Variables
    * def randAlpha = commonutilClass.getRandomAlpha(14)
    * print 'Random Alpha is - ', randAlpha

  #-----------------------------------------------------------------------------------------
  #Getting Value From Excel As JSON
  #-----------------------------------------------------------------------------------------
  @excelsample
  Scenario: Scenario To Access Data Values From Excel As Json
    * json excelJson = excelClass.openExcelAndGetColumnValuesToJson('data','Sheet1',0,'Record-001')
    * print 'Json is - ', excelJson
    * print 'Name is', karate.jsonPath(excelJson, "$.name")

  #-----------------------------------------------------------------------------------------
  #Getting Value From Excel As MAP
  #-----------------------------------------------------------------------------------------
  @excelsample
  Scenario: Scenario To Access Data Values From Excel As Json
    * excelClass.openExcelAndGetColumnValuesToMap('data','Sheet1',0,'Record-001')
    * print 'Map: ', excelClass.getRowHashMap()
    * def name = excelClass.getValueForKey('name')
    * print 'Name is - ', name

  #-----------------------------------------------------------------------------------------
  #Number Sample
  #-----------------------------------------------------------------------------------------
  @numbersample
  Scenario: Scenario To Use Numbers
    * def x = '10'
    * print x
    * match x == '#string'
    * def y = x * 1
    * print 'Converted x to int by multiplying it with 1 - ', y
    * print 'Converted x using parseInt - ', parseInt(x)
    * match parseInt(x) == '#number'
    * print 'Removing Decimals  using tilde ~~ symbol - ', ~~y
    * match y == '#number'
    * def z = 12345678909876543
    * print 'Large Numbers without using Big Decimal: ', z * 265231
    * print 'Large Numbers without any fomrat issue using Big Decimal: ', new java.math.BigDecimal(z * 265231)

  #-----------------------------------------------------------------------------------------
  #Example Scenario For Using JsonPath
  #-----------------------------------------------------------------------------------------
  @jsonPath
  Scenario: Json Path Sample Scenario
    Given def path = dataClass.JSON_FOLDER
    Given def file = path + 'getUserData.json'
    Given print 'Json File is: ', file
    Given def json = read(file)
    Then print 'json - ', json
    Given url baseuri
    Then path '/users'
    And request json
    When method get
    Then status 200
    And match response contains $.[0]
    * json respJson = response
    Then print 'First User: ', karate.jsonPath(respJson, "$.[0].username")

  #-----------------------------------------------------------------------------------------
  #Example Scenario For Using Scenario Outline
  #-----------------------------------------------------------------------------------------
  @scenariooutline @train1
  Scenario Outline: Scenario To Update json Attribute Value From Examples
    Given def path = dataClass.JSON_FOLDER
    Given def file = path + 'putUserData.json'
    Given print 'Json File is: ', file
    Given def json = read(file)
    Given print 'json - ', json
    Given path dataClass.PUT_USER_DETAILS
    Then request json
    When method put
    Then status 200
    Then print 'Response: ', response
    * assert responseStatus == 200
    * match responseStatus contains 200
    * match responseStatus == 200
    * match responseStatus == '#number'
    * def format = (responseStatus == 200 ? {format: 'Status format is number'} : {format: 'Status format is invalid'})
    Then print 'Status Format: ', format
    And assert responseTime < 5000
    Then print 'Response Time: ', responseTime

    @train1
    Examples: 
      | title      |
      | sunshine   |
      | daisy      |
      | watermelon |

    @train2
    Examples: 
      | title |
      | Mr    |
      | Ms    |
      | Mrs   |
