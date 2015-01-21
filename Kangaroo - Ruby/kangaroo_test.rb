require_relative 'Point.rb'
require_relative 'Grid.rb'
require_relative 'Kangaroo.rb'
require 'test/unit'
class Kangaroo_Test <Test::Unit::TestCase

  def setup
    @kangaroo = Kangaroo.new (Grid.new 4)
  end

  def test_hop
    10.times do
      @kangaroo.hop! @kangaroo.point+Point.new(1,2)
    end
    assert(@kangaroo.point==Point.new(10,20),'Error in hop: Kangaroo is not hopping')
    assert(@kangaroo.no_of_moves==10,'Number of moves is not calculated properly')
  end

  def test_move
    assert_block('Error in move: Kangaroo hops to out of bound position.')do
      @kangaroo.move(Point.new(4,5))
      @kangaroo.point==Point.new(0,0)
    end

    assert_block('Error in move: Kangaroo is not moving.')do
      @kangaroo.move (Point.new(3,2))
      @kangaroo.point==Point.new(3,2)
    end
  end

  def test_at_home
    assert_block('Kangaroo doesnt reach home')do
      @kangaroo.hop!Point.new(3,3)
      @kangaroo.at_home?
    end
  end
end