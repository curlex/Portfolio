class Point
  attr_reader :x,:y

  def initialize x,y
    @x = x
    @y = y
  end
  def + point
    Point.new(@x+point.x,@y+point.y)
  end
  def == point
    if(@x==point.x &&@y==point.y)
      true
    else
      false
    end
  end
end
