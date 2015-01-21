require_relative 'Die.rb'
require_relative 'Point.rb'
require 'test/unit'
class Die_Test <Test::Unit::TestCase
  def setup
     @die = Die.new
  end

  def test_initialize
    assert(@die.north==Point.new(0,1),'North was not initialised properly')
    assert(@die.east==Point.new(1,0),'East was not initialised properly')
    assert(@die.south==Point.new(0,-1),'South was not initialised properly')
    assert(@die.west==Point.new(-1,0),'West was not initialised properly')
  end

  def test_throw
    8.times{
      assert_block('Throw method is not returning point object with the right values.')do
          case @die.throw
            when @die.east
              true
            when @die.north
              true
            when @die.west
              true
            when @die.south
              true
            else
              false
          end
      end
    }
  end
end