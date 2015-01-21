require_relative 'grid.rb'
require_relative 'kangaroo.rb'
require_relative 'die.rb'

begin
  puts "Enter dimension of the Grid (>=1):"
  size = gets.chomp
  size = size.to_i
end while size<1

g = Grid.new size
kang = Kangaroo.new g
d = Die.new

while !kang.at_home? do
  kang.move d.throw
end



