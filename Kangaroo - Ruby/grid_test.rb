require_relative 'Point.rb'
require_relative 'Grid.rb'
require 'test/unit'
class Grid_Test <Test::Unit::TestCase
  def setup
    @grid = Grid.new(4)
    @point = Point.new(3,3)
  end
  def test_1
    assert_equal(4,@grid.size,'Size variable not initialised properly')
  end
  def test_2
    assert_not_equal(true,(@grid.out_of_bound? @point),'Out of bound not working')
  end
end