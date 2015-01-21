require_relative 'question.rb'
class BasicQuestion < Question

  def initialize
    @range = (1..9)
    @operation_one = '+'
    @operation_two = '-'
    super
  end


end