
assert_rows_are_unique = function(df, cols, filename = "../view/verify/assert_rows_are_unique.tsv" ) {
	dir.create( dirname(filename), showWarnings = F, recursive = T )
	dup_rows = duplicated_rows_(df, cols)

	export( dup_rows, filename )
	assert_that( is_empty(dup_rows) )
	#> ../view/verify/assert_rows_are_unique.tsv
}

assert_inclusion = function(subset, superset, fk_subset, fk_superset = fk_subset, filename = "assert_inclusion.tsv", validate = F) {
	filename = paste0("../view/verify/", filename)
	dir.create( dirname(filename), showWarnings = F, recursive = T )

	# subset - superset == ∅  
	# subset ⊆ superset 
	no_subset__missing_instances = subset %>%
		anti_join( superset, by = setNames(fk_superset, fk_subset) )

	export(no_subset__missing_instances, filename)
	if (validate) {
		validate_that( is_empty(no_subset__missing_instances) )
	} else {
		assert_that( is_empty(no_subset__missing_instances) )
	}
	#> ../view/verify/assert_inclusion.tsv
}

assert_all_have_attribute = function( df, columns, filename = "../view/verify/assert_all_have_attribute.tsv", validate = F ) {
	dir.create( dirname(filename), showWarnings = F, recursive = T )
	instances_with_no_attribute_value = df %>%
		filter_( is_na(str_trim( columns )) )

	export( instances_with_no_attribute_value, filename )
	if (validate) {
		validate_that( is_empty(instances_with_no_attribute_value) )
	} else {
		assert_that( is_empty(instances_with_no_attribute_value) )
	}
	#> ../view/verify/assert_all_have_attribute.tsv
}

assert_no_intersection = function(df1, df2, fk1, fk2 = fk1, filename = "assert_no_intersection.tsv" ) {
	filename = paste0("../view/verify/", filename)
	dir.create( dirname(filename), showWarnings = F, recursive = T )
	# df1 ∩ df2 == ∅  
	no_intersection__common_instances = df1 %>%
		inner_join( df2, by = setNames(fk2, fk1) )

	export(no_intersection__common_instances, filename)
	assert_that( is_empty(no_intersection__common_instances) )
	#> ../view/verify/assert_no_intersection.tsv
}

assert_equal_set = function(subset, superset, fk_subset, fk_superset = fk_subset, filename = "assert_equal_set.tsv", validate = F) {
	filename = paste0("../view/verify/", filename)
	dir.create( dirname(filename), showWarnings = F, recursive = T )

	assert_inclusion(subset, superset, fk_subset, fk_superset, filename, validate)
	assert_inclusion(superset, subset, fk_superset, fk_subset, filename, validate)
	#> ../view/verify/assert_inclusion.tsv
}

assert_subset = function(subset, superset) {
  assert_that( length( setdiff(subset, superset) ) == 0 )
}
