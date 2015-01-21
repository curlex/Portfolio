require_relative 'point.rb'

class Kangaroo
  attr_reader :point ,:no_of_moves
  def initialize grid
    @grid = grid
    @point = Point.new(0,0)
    @no_of_moves = 0

  end

  def hop! hop_point
    @point = hop_point
    @no_of_moves+=1
    puts "Hopped to: (#{@point.x} , #{@point.y})"
  end
  
  def at_home?
    if @point.x == @grid.size-1 && @point.y == @grid.size-1
      puts "Finished in #{@no_of_moves} hops"
      return true
    end
    return false
  end

  def move moving_point
    unless @grid.out_of_bound? point+moving_point
      hop! point+moving_point
    else
      puts "Oops hit the boundary:(#{(point+moving_point).x} , #{(point+moving_point).y})"
    end
  end
end