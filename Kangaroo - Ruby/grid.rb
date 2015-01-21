#!/usr/bin/env ruby
require_relative "point.rb"
class Grid
  attr_reader :size

  def initialize size
    @size = size
    @g = Array.new(@size){ Array.new(@size,"| |")}   #creates printable grid
  end

  #Prints out the grid
  def print_grid kang
    @g[@size-1][@size-1] = "|H|"
    @g[kang.x][kang.y] = "|K|"
    @g.each do |x|
      x.each do |y|
        print [y]
      end
      puts
    end
    @g[kang.x][kang.y] = "| |"
  end

  def out_of_bound? point
    if (point.x < 0 || point.y < 0 || point.x > @size-1 || point.y > @size-1)
      true
    else
      false
    end
  end
end
