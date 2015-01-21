require_relative 'point.rb'
class Die
  attr_reader :north,:east,:south,:west
  def initialize
    @north = Point.new 0,1
    @east = Point.new 1,0
    @south = Point.new 0,-1
    @west = Point.new -1,0
  end

  def throw
    num = Random.rand(4)+1
    case num
      when 1
        return @north
      when 2
        return @east
      when 3
        return @south
      when 4
        return @west
    end

 end

end

