require_relative 'Point.rb'
require 'test/unit'
class Point_Test <Test::Unit::TestCase
  def setup
    @point = Point.new(0,0)
  end

  def test_plus
    assert_equal(Point.new(2,9),@point+Point.new(2,3)+Point.new(0,6),"Addition method is not working.")
  end

  def test_equality
    assert_equal(true,@point==Point.new(0,0),"Equal method not working.")
    assert_equal(false,@point==Point.new(4,7),"Equal method not working")
  end
end