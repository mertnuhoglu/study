context("test-test01")

test_that("multiplication works", {
  expect_equal(2 * 2, 4)
})

test_that("can use library functions", {
	ex03_fun()
  expect_equal(1, 1)
})
